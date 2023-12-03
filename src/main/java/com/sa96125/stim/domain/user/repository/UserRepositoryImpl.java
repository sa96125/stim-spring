package com.sa96125.stim.domain.user.repository;

import com.sa96125.stim.common.api.exception.custom.DuplicateAccountException;
import com.sa96125.stim.common.api.exception.custom.ResourceNotFoundException;
import com.sa96125.stim.domain.user.service.User;
import com.sa96125.stim.domain.user.service.port.UserRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    
    private final UserJpaRepository userJpaRepository;
    
    @Transactional
    @Override
    public UserEntity save(User user) {
        UserEntity userEntity = user.toEntity();
        try {
            return userJpaRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            log.info(e.getMessage());
            throw new DuplicateAccountException("Duplicate account: " + user.getEmail());
        } catch (JpaSystemException e) {
            log.info(e.getMessage());
            throw new ResourceNotFoundException("Failed to save user: " + user.getEmail());
        }
    }
    
    @Override
    public UserEntity findByUserId(String userId) {
        try {
            return userJpaRepository.findByUserId(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("Failed to fetch user with userId: " + userId));
        } catch (NoResultException e) {
            log.info(e.getMessage());
            throw new ResourceNotFoundException("Failed to fetch user with userId: " + userId);
        }
    }
    
    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }
}
