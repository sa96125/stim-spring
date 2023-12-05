package com.sa96125.stim.domain.feed.service.port;

import com.sa96125.stim.domain.user.repository.UserEntity;

import java.util.Optional;

public interface FeedUserAdapter {
    Optional<UserEntity> findById(String userId);
}
