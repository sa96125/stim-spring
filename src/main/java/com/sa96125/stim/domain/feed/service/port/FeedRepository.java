package com.sa96125.stim.domain.feed.service.port;

import com.sa96125.stim.domain.feed.repository.FeedEntity;

import java.util.Optional;

public interface FeedRepository {
    FeedEntity save(FeedEntity feedEntity);
    
    FeedEntity update(FeedEntity feedEntity);
    
    void delete(String feedId);
    
    Optional<FeedEntity> findById(String feedId);
}
