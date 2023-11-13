package com.example.custom_authentication.config;

import com.example.custom_authentication.config.security.filters.CustomAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextHolderFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
  private final CustomAuthenticationFilter customAuthenticationFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .addFilterAt(customAuthenticationFilter, SecurityContextHolderFilter.class) // The 2nd parameter is where the filter will be applied.
            .authorizeHttpRequests(auth-> auth
                            .anyRequest()
                            .authenticated())
            .build();

  }

}
