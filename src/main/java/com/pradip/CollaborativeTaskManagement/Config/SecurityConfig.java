package com.pradip.CollaborativeTaskManagement.Config;

import com.pradip.CollaborativeTaskManagement.Security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/login").permitAll()
                        .requestMatchers("/api/users/register").permitAll() // Allow public access to login
                        .requestMatchers("/ws/**").permitAll()  // WebSocket should be accessible
                        .requestMatchers("/api/users/**").hasAnyRole("USER", "ADMIN") // USER and ADMIN can access user endpoints
                        .requestMatchers("/tasks/**").hasAnyRole("USER", "ADMIN") // USER and ADMIN can access task endpoints
                        .requestMatchers("/tasks/admin/**").hasRole("ADMIN") // Only ADMIN can access admin endpoints
                        .requestMatchers("/projects/**").hasAnyRole("USER", "ADMIN") // USER and ADMIN can access project endpoints
                        .requestMatchers("/projects/admin/**").hasRole("ADMIN") // Only ADMIN can access admin endpoints
                        .requestMatchers("/api/notifications/**").hasAnyRole("USER", "ADMIN") // USER and ADMIN can access notification endpoints
                        .requestMatchers("/api/comments/**").hasAnyRole("USER", "ADMIN") // USER and ADMIN can access comment endpoints

                        .anyRequest().authenticated() // All other requests require authentication
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

