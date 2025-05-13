package com.bellamyphan.spring_backend.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
    private final TokenAuthorizationFilter tokenAuthorizationFilter;

    // Define security filter chain: JWT auth, stateless, and role-based access control
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Initializing security filter chain configuration");

        http
                .cors(cors ->
                        cors.configurationSource(new WebConfig().corsConfigurationSource())) // ✅ tell security to use CORS settings
                .csrf(csrf -> {
                    csrf.disable();
                    logger.debug("CSRF protection disabled (JWT stateless)");
                })
                .authorizeHttpRequests(auth -> {
                    logger.debug("Defining authorization rules for endpoints");
                    auth.requestMatchers("/api/auth/login").permitAll();
                    logger.debug("Permitting unauthenticated access to /api/auth/login");
                    auth.requestMatchers(HttpMethod.POST, "/api/users").permitAll();
                    logger.debug("Permitting unauthenticated POST to /api/users");
                    auth.requestMatchers(HttpMethod.GET, "/api/transactions/**").hasAnyRole("USER", "ADMIN");
                    logger.debug("Permitting GET /api/transactions/** for USER and ADMIN roles");
                    auth.requestMatchers("/api/transactions/**").hasRole("ADMIN");
                    logger.debug("Restricting POST, PUT, DELETE /api/transactions/** to ADMIN role");
                    auth.anyRequest().authenticated();
                    logger.debug("All other requests require authentication");
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                    logger.debug("Session management set to STATELESS");
                })
                .addFilterBefore(tokenAuthorizationFilter,
                        org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        logger.info("Security filter chain successfully configured");
        return http.build();
    }
}
