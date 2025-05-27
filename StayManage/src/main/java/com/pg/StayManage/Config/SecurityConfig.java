package com.pg.StayManage.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableTransactionManagement
public class SecurityConfig {

        @Autowired
        private JwtFilter jwtAuthFilter;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http.csrf(csrf -> csrf.disable())
                                .cors(cors -> cors.configurationSource(corsConfigurationSource()));

                http.addFilterBefore(jwtAuthFilter, AuthorizationFilter.class);

                http.authorizeHttpRequests(auth -> auth
                                .requestMatchers("/api/admin/register", "/api/admin/generateRentReport",
                                                "/api/admin/login",
                                                "/api/tenants/tenantregister", "/api/tenants/tenantlogin",
                                                "/uploads/**", "/contactus/contact/")
                                .permitAll()
                                .requestMatchers("/api/rooms/**", "/api/tenants", "/api/food", "/rent",
                                                "/payment", "/api/notification")

                                .hasRole("ADMIN")
                                .requestMatchers("/tenant/features")
                                .hasRole("TENANT")
                                .anyRequest()
                                .authenticated());
                http.sessionManagement(session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                return http.build();
        }

        @Bean
        public UrlBasedCorsConfigurationSource corsConfigurationSource() {
                CorsConfiguration corsConfig = new CorsConfiguration();
                corsConfig.setAllowedOrigins(Arrays.asList("http://192.168.0.103:4200")); // Allow Angular URL
                corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
                corsConfig.setAllowCredentials(true);

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", corsConfig);

                CorsConfiguration imageCorsConfig = new CorsConfiguration();
                imageCorsConfig.setAllowedOrigins(Arrays.asList("http://192.168.0.103:4200"));
                imageCorsConfig.setAllowedMethods(Arrays.asList("GET"));
                imageCorsConfig.setAllowedHeaders(Arrays.asList("Content-Type"));

                source.registerCorsConfiguration("/uploads/**", imageCorsConfig);

                return source;
        }
}
