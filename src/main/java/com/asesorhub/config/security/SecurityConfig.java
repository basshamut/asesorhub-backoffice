package com.asesorhub.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers(
                        "/unauthenticated",
                        "/oauth2/**",
                        "/login/**",
                        "/swagger*/**",
                        "/v3/api-docs/**",
                        "/console/**",
                        "/swagger*/**",
                        "/v3/api-docs/**",
                        "/error"
                ).permitAll();
        http
                .authorizeHttpRequests(authz -> authz.anyRequest().authenticated())
                .addFilterAfter(new JwtKeycloakSecurityFilter(), BasicAuthenticationFilter.class);


        return http.build();
    }
}