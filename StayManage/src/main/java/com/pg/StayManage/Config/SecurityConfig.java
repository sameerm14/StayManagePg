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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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

                http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                http.authorizeHttpRequests(auth -> auth
                                .requestMatchers("/api/admin/register", "/api/admin/generateRentReport",
                                                "/api/admin/login","/api/admin/api/health",
                                                "/api/tenants/tenantregister", "/api/tenants/tenantlogin",
                                                "/uploads/**", "/contactus/contact/")
                                .permitAll()
                                .requestMatchers("/api/rooms/**", "/api/tenants/**", "/api/food/**", "/rent/**",
                                                "/payment/**", "/api/notification/**")

                                .hasRole("ADMIN")
                                .requestMatchers("/api/tenants/**","/api/food/**")
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
                corsConfig.setAllowedOrigins(Arrays.asList("https://staymanage.in","https://www.staymanage.in")); // Allow Angular URL
                corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
                corsConfig.setAllowCredentials(true);

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", corsConfig);

                return source;
        }
}
