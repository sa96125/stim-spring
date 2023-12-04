package com.sa96125.stim.domain.user.service;

import com.sa96125.stim.common.api.exception.custom.DuplicateAccountException;
import com.sa96125.stim.common.api.exception.custom.ResourceNotFoundException;
import com.sa96125.stim.common.security.port.UserDetailsServiceAdapter;
import com.sa96125.stim.domain.user.controller.port.UserService;
import com.sa96125.stim.domain.user.repository.UserEntity;
import com.sa96125.stim.domain.user.service.port.UserRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsServiceAdapter, UserService {
    
    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
        
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(userEntity.getRole().getValue()));
        
        return new org.springframework.security.core.userdetails.User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
    
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public User create(User user) {
        try {
            UserEntity userEntity = user.toEntity();
            return User.from(userRepository.save(userEntity));
        } catch (DataIntegrityViolationException e) {
            log.error("Duplicate account: " + e.getMessage());
            throw new DuplicateAccountException("Duplicate account with Email: " + user.getEmail());
        } catch (Exception e) {
            log.error("Failed to create user: " + e.getMessage());
            throw new ResourceNotFoundException("Failed to create user with Email: " + user.getEmail());
        }
    }
    
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public User update(User user) {
        try {
            UserEntity userEntity = user.toEntity();
            return User.from(userRepository.save(userEntity));
        } catch (Exception e) {
            log.error("Failed to update user: " + e.getMessage());
            throw new ResourceNotFoundException("Failed to update user with Email: " + user.getUserId());
        }
    }
    
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public User delete(String userId) {
        try {
            userRepository.delete(userId);
            return User.builder().userId(userId).build();
        } catch (Exception e) {
            log.error("Failed to delete user: " + e.getMessage());
            throw new ResourceNotFoundException("Failed to delete user with userId: " + userId);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public User getById(String userId) {
        try {
            UserEntity userEntity = userRepository.findById(userId)
                    .orElseThrow(() -> new NoResultException("No user found with userId: " + userId));
            
            return User.from(userEntity);
        } catch (Exception e) {
            log.error("Failed to fetch user: " + e.getMessage());
            throw new ResourceNotFoundException("Failed to fetch user with userId: " + userId);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public User getByEmail(String email) {
        try {
            UserEntity userEntity = userRepository.findByEmail(email)
                    .orElseThrow(() -> new NoResultException("No user found with userEmail: " + email));
            
            return User.from(userEntity);
        } catch (JpaSystemException | NoResultException e) {
            log.error("Failed to fetch user: " + e.getMessage());
            throw new ResourceNotFoundException("Failed to fetch user with userEmail: " + email);
        }
    }
}
