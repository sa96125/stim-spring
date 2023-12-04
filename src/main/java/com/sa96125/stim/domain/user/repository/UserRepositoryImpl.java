package com.sa96125.stim.domain.user.repository;

import com.sa96125.stim.domain.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    
    private final UserJpaRepository userJpaRepository;
    
    @Override
    public UserEntity save(UserEntity userEntity) {
        return userJpaRepository.save(userEntity);
    }
    
    @Override
    public UserEntity update(UserEntity userEntity) {
        return userJpaRepository.save(userEntity);
    }
    
    @Override
    public void delete(String userId) {
        userJpaRepository.deleteByUserId(userId);
    }
    
    @Override
    public Optional<UserEntity> findById(String userId) {
        return userJpaRepository.findByUserId(userId);
    }
    
    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }
}
