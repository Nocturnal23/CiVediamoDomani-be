package com.progettoweb.civediamodomanibe.core.config;

import com.progettoweb.civediamodomanibe.core.utils.Constants.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;
import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowedMethods(List.of("POST", "GET", "DELETE", "PUT", "OPTIONS", "PATCH"));
        config.setExposedHeaders(Collections.singletonList(HttpHeaders.AUTHORIZATION));
//        config.setExposedHeaders(
//                List.of(
//                        "X-Current-Page, X-Num-Current-Page-Elements, X-Num-Total-Pages, X-Num-Total-Elements, X-Request-Outcome",
//                        "Content-Type",
//                        "api_key",
//                        "Authorization"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/swagger-resources/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers(Endpoint.Authentication + "/**", "/api/v1/authenticate", "/oauth/**").permitAll()
                        .requestMatchers(Endpoint.Users, Endpoint.Users + "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, Endpoint.Events, Endpoint.Events + "/**").permitAll()
                        .requestMatchers(HttpMethod.GET, Endpoint.Events, Endpoint.Events + "/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, Endpoint.Events, Endpoint.Events + "/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, Endpoint.Events, Endpoint.Events + "/**").permitAll()
                        .requestMatchers(Endpoint.Categories, Endpoint.Categories + "/**").permitAll())
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests().anyRequest().authenticated()
                .and().build();
    }
}
