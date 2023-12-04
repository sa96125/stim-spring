package com.sa96125.stim.domain.feed.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FeedJpaRepository extends CrudRepository<FeedEntity, Long> {
    void deleteByFeedId(String feedId);
    
    Optional<FeedEntity> findByFeedId(String feedId);
}
