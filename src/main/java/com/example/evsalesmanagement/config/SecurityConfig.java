// package com.example.evsalesmanagement.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//         httpSecurity.csrf(csrf -> csrf.disable());

//         httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

//         httpSecurity.authorizeHttpRequests(
//                 auth -> auth
//                         .requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll() // endpoint cong khai
//                         .anyRequest().authenticated()); // endpoint bao mat

//         return httpSecurity.build();
//     }
// }
