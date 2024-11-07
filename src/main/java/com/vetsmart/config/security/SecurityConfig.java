package com.vetsmart.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
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
                        ).permitAll()
                        .anyRequest().authenticated()
                ).authorizeHttpRequests(authz -> authz.anyRequest().authenticated())
                .addFilterAfter(new JwtAuth0SecurityFilter(), BasicAuthenticationFilter.class);


        return http.build();
    }
}