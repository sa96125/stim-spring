package com.sa96125.stim.common.security.port;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailsServiceAdapter extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
}
