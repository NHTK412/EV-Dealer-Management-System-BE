package com.example.evsalesmanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.evsalesmanagement.exception.AccessDeniedHandlerException;
import com.example.evsalesmanagement.exception.AuthenticationEntryPointException;
import com.example.evsalesmanagement.filter.JwtAuthFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Autowired
    private AccessDeniedHandlerException accessDeniedHandlerException;

    @Autowired
    private AuthenticationEntryPointException authenticationEntryPointException;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable());

        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**", "/images/**").permitAll() // endpoint
                                                                                                                    // cong
                        // khai
                        .anyRequest().authenticated()); // endpoint bao mat

        httpSecurity.exceptionHandling(ex -> ex
                .authenticationEntryPoint(authenticationEntryPointException) // 401
                .accessDeniedHandler(accessDeniedHandlerException)); // 403

        httpSecurity.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
