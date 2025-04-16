package com.asesorhub.config.security;

import com.asesorhub.config.security.jwt.JWTAuthorizationFilter;
import com.asesorhub.config.security.provider.CustomAuthenticationProvider;
import com.asesorhub.exception.MvcRequestMatcherConfigurationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.Collections;

import static com.asesorhub.utils.Constants.API_VERSION_PATH;
import static com.asesorhub.utils.Constants.LOGIN_PATH;
import static com.asesorhub.utils.Constants.LOGIN_URL;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final CustomAuthenticationProvider customAuthenticationProvider;

    private final String[] WHITE_LIST = {
            "/swagger*/**",
            "/v3/api-docs/**",
            "/error",
            LOGIN_URL,
            API_VERSION_PATH + "/accounts"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    try {
                        // Create MvcRequestMatchers for MVC endpoints
                        for (String pattern : WHITE_LIST) {
                            auth.requestMatchers(new MvcRequestMatcher(introspector, pattern)).permitAll();
                        }
                    } catch (Exception e) {
                        throw new MvcRequestMatcherConfigurationException("Failed to configure MVC request matchers", e);
                    }
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(getJwtAuthorizationFilter())
        ;
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(customAuthenticationProvider));
    }

    public JWTAuthorizationFilter getJwtAuthorizationFilter() {
        return new JWTAuthorizationFilter(authenticationManager());
    }

}
