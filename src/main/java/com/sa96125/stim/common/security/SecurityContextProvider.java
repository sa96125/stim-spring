package com.sa96125.stim.common.security;

import com.sa96125.stim.common.api.exception.custom.AuthenticationFailedException;
import com.sa96125.stim.common.security.port.SecurityContextAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityContextProvider implements SecurityContextAdapter {
    public String authenticateUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationFailedException("Authentication failed");
        }
        
        return authentication.getName();
    }
}
