package com.progettoweb.checasavuoibe.config;

import com.google.common.collect.ImmutableList;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(ImmutableList.of("*"));
        config.setAllowedHeaders(ImmutableList.of("*"));
        config.setAllowedMethods(ImmutableList.of("POST", "GET", "DELETE", "PUT", "OPTIONS", "PATCH"));
        config.setExposedHeaders(
                ImmutableList.of(
                        "X-Current-Page, X-Num-Current-Page-Elements, X-Num-Total-Pages, X-Num-Total-Elements, X-Request-Outcome",
                        "Content-Type",
                        "api_key",
                        "Authorization"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
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
        http.csrf().disable();
//                .headers()
//                .frameOptions()
//                .sameOrigin()
//                .and()
//                .addFilterBefore(uidRequestFilter, CsrfFilter.class)
//                .authorizeRequests()

//                .antMatchers(HttpMethod.POST, "/users")
//                .antMatchers(HttpMethod.PUT, "/users/{^[\\d]$}")
//                .antMatchers(HttpMethod.DELETE, "/users/{^[\\d]$}")
//
//                .antMatchers(HttpMethod.POST, "/users/filter", "/users/export")
//                .antMatchers(HttpMethod.GET, "/users/{^[\\d]$}")



//                .anyRequest().authenticated().and()
//                .exceptionHandling()
//                .authenticationEntryPoint(authEntryPoint())
//                .accessDeniedHandler(accessDeniedHandler())
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
