package com.seminarroom.edu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors() // ✅ ENABLE CORS here
                .and()
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/**",
                                "/api/courses/**",
                                "/api/modules/**",
                                "/api/courses/**/modules/**/reading-materials/**", // ✅ You can be specific too
                                "/api/modules/**/reading-materials",               // ✅ Also this pattern
                                "/api/modules/**/video-lectures",                  // ✅ For videos
                                "/api/courses/**/modules/**/assignments/**",        // ✅ Assignments
                                "/api/**" // 👈 Add all public APIs you want to expose
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(); // or .formLogin() if using session auth

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
