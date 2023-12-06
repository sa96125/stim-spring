package com.sa96125.stim.common.security;

import com.sa96125.stim.common.api.exception.custom.AuthenticationFailedException;
import com.sa96125.stim.common.jwt.JwtProvider;
import com.sa96125.stim.domain.user.service.port.UserDetailsServiceAdapter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    
    private final UserDetailsServiceAdapter userDetailsServiceAdapter;
    
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        
        try {
            String bearerToken = request.getHeader("Authorization");
            String token = JwtProvider.resolveToken(bearerToken);
            
            if (token != null && JwtProvider.validateToken(token)) {
                String email = JwtProvider.getEmailFromToken(token);
                UserDetails userDetails = userDetailsServiceAdapter.loadUserByUsername(email);
                authenticateUser(userDetails);
            }
            
        } catch (AuthenticationFailedException e) {
            handleAuthenticationFailure(response, e.getMessage());
        } catch (Exception e) {
            handleGenericError(response);
        }
        
        filterChain.doFilter(request, response);
    }
    
    private void authenticateUser(UserDetails userDetails) {
        if (userDetails == null) return;
        
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
        
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    
    private void handleAuthenticationFailure(HttpServletResponse response, String errorMessage) throws IOException {
        SecurityContextHolder.clearContext();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(errorMessage);
        log.error("Authentication failed: {}", errorMessage);
    }
    
    private void handleGenericError(HttpServletResponse response) throws IOException {
        SecurityContextHolder.clearContext();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Authentication failed. Please check your credentials and try again.");
        log.error("Error processing authentication.");
    }
}