package com.sa96125.stim.domain.user.repository.port;

import com.sa96125.stim.domain.user.repository.UserEntity;

import java.util.Optional;

public interface UserRepository {
    UserEntity save(UserEntity userEntity);
    
    UserEntity update(UserEntity userEntity);
    
    void delete(String userId);
    
    Optional<UserEntity> findById(String userId);
    
    Optional<UserEntity> findByEmail(String email);
}
