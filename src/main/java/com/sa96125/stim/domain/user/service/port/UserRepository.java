package com.sa96125.stim.domain.user.service.port;

import com.sa96125.stim.domain.user.repository.UserEntity;
import com.sa96125.stim.domain.user.service.User;

import java.util.Optional;

public interface UserRepository {
    UserEntity save(User user);
    UserEntity findByUserId(String userId);
    Optional<UserEntity> findByEmail(String email);
}
