package com.sa96125.stim.domain.user.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserJpaRepository extends CrudRepository<UserEntity, Long> {
    void deleteByUserId(String userId);
    
    Optional<UserEntity> findByUserId(String userId);
    
    Optional<UserEntity> findByEmail(String email);
}
