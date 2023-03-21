package com.progettoweb.civediamodomanibe.core.config;

import com.google.common.collect.ImmutableList;
import com.progettoweb.civediamodomanibe.core.config.authfilters.JwtAuthFilter;
import com.progettoweb.civediamodomanibe.core.config.authfilters.UsernamePasswordAuthFilter;
import com.progettoweb.civediamodomanibe.core.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Long MAX_AGE = 3600L;
    private static final int CORS_FILTER_ORDER = -102;

    private final UserAuthenticationProvider userAuthenticationProvider;

    public SecurityConfiguration(UserAuthenticationProvider userAuthenticationProvider) {
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(ImmutableList.of("*"));
        config.setAllowedHeaders(ImmutableList.of("*"));
        config.setAllowedMethods(ImmutableList.of(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.POST.name()));
        config.setMaxAge(MAX_AGE);
//        config.setExposedHeaders(
//                ImmutableList.of(
//                        "X-Current-Page, X-Num-Current-Page-Elements, X-Num-Total-Pages, X-Num-Total-Elements, X-Request-Outcome",
//                        "Content-Type",
//                        "api_key",
//                        "Authorization"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean<>( new CorsFilter(source));
        bean.setOrder(CORS_FILTER_ORDER);
        return bean;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(
                        "/users/auth",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**")
                .antMatchers(HttpMethod.OPTIONS, "/**")
                // allow anonymous resource requests
                .antMatchers(
                        "/api/**",
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(authEntryPoint())
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                .addFilterBefore(new UsernamePasswordAuthFilter(userAuthenticationProvider), CsrfFilter.class)
                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), UsernamePasswordAuthFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                .headers().frameOptions().sameOrigin()
//                .and()
                .authorizeRequests()

                .antMatchers("**").hasRole(Constants.StringRole.AMMINISTRATORE)
                .antMatchers("/login*").permitAll()
                .anyRequest().authenticated();
    }

    @Slf4j
    static class AccessDeniedCustomHandler implements AccessDeniedHandler {
        @Override
        public void handle(
                HttpServletRequest request,
                HttpServletResponse response,
                AccessDeniedException accessDeniedException)
                throws IOException {
            log.error(accessDeniedException.getMessage());
            response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
        }
    }

    static class AuthEntryPoint implements AuthenticationEntryPoint {
        @Override
        public void commence(
                HttpServletRequest request,
                HttpServletResponse response,
                AuthenticationException authException) throws IOException {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        }

    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedCustomHandler();
    }

    @Bean
    public AuthEntryPoint authEntryPoint() {
        return new AuthEntryPoint();
    }
}
