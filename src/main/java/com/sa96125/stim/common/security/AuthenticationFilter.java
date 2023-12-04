package com.sa96125.stim.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa96125.stim.common.api.exception.custom.AuthenticationFailedException;
import com.sa96125.stim.common.api.util.HttpUtils;
import com.sa96125.stim.common.jwt.JwtProvider;
import com.sa96125.stim.domain.auth.controller.RequestLogin;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public Authentication attemptAuthentication(@NonNull HttpServletRequest request,
                                                @NonNull HttpServletResponse response) throws AuthenticationException {
        try {
            RequestLogin credentials = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);
            
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    credentials.getEmail(),
                    passwordEncoder.encode(credentials.getPassword()),
                    Collections.emptyList()
            );
            
            return getAuthenticationManager().authenticate(authentication);
        } catch (IOException e) {
            log.debug("Authentication failed: {}", e.getMessage());
            throw new AuthenticationFailedException("Authentication Failed.");
        }
    }
    
    @Override
    protected void successfulAuthentication(@NonNull HttpServletRequest request,
                                            @NonNull HttpServletResponse response,
                                            @NonNull FilterChain chain,
                                            @NonNull Authentication authResult) {
        String userId = ((UserDetails) authResult.getPrincipal()).getUsername();
        
        String accessToken = JwtProvider.generateAccessToken(userId);
        String refreshToken = JwtProvider.generateRefreshToken(userId);
        Cookie cookie = HttpUtils.createCookie("refresh-token", refreshToken, 1800);
        
        response.addHeader("Authorization", "Bearer " + accessToken);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addCookie(cookie);
    }
}