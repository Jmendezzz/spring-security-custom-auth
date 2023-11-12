package com.example.custom_authentication.config.security.filters;


import com.example.custom_authentication.config.security.authentication.CustomAuthentication;
import com.example.custom_authentication.config.security.managers.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {
  private final CustomAuthenticationManager authenticationManager;
  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    CustomAuthentication customAuth = new CustomAuthentication(false, String.valueOf(request.getHeader("key")));
    Authentication userAuth = authenticationManager.authenticate(customAuth);
    if(userAuth.isAuthenticated()){
      SecurityContextHolder.getContext().setAuthentication(userAuth); // Set the user to the context of the applcation.
      filterChain.doFilter(request, response); // Only when authentication works

    }


  }
}
