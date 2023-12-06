package com.sa96125.stim.domain.feed.repository;

import com.sa96125.stim.domain.feed.repository.port.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FeedRepositoryImpl implements FeedRepository {
    
    private final FeedJpaRepository feedJpaRepository;
    
    @Override
    public FeedEntity save(FeedEntity feedEntity) {
        return feedJpaRepository.save(feedEntity);
    }
    
    @Override
    public FeedEntity update(FeedEntity feedEntity) {
        return feedJpaRepository.save(feedEntity);
    }
    
    @Override
    public void delete(String feedId) {
        feedJpaRepository.deleteByFeedId(feedId);
    }
    
    @Override
    public Optional<FeedEntity> findById(String feedId) {
        return feedJpaRepository.findByFeedId(feedId);
    }
}
