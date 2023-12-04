package com.sa96125.stim.domain.feed.service;

import com.sa96125.stim.common.api.exception.custom.AuthenticationFailedException;
import com.sa96125.stim.common.api.exception.custom.DuplicateAccountException;
import com.sa96125.stim.common.api.exception.custom.ResourceNotFoundException;
import com.sa96125.stim.domain.feed.controller.port.FeedService;
import com.sa96125.stim.domain.feed.repository.FeedEntity;
import com.sa96125.stim.domain.feed.service.port.FeedRepository;
import com.sa96125.stim.domain.feed.service.port.SecurityContextAdapter;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {
    
    private final FeedRepository feedRepository;
    private final SecurityContextAdapter securityContextAdapter;
    
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Feed create(Feed feed) {
        try {
            String userId = securityContextAdapter.authenticateUser();
            feed.setUserId(userId);
            FeedEntity feedEntity = feed.toEntity();
            return Feed.from(feedRepository.save(feedEntity));
        } catch (DataIntegrityViolationException e) {
            log.error("Duplicate feed: " + e.getMessage());
            throw new DuplicateAccountException("Duplicate feed with feedId: " + feed.getFeedId());
        } catch (AuthenticationFailedException e) {
            log.error("User does not have permission: " + e.getMessage());
            throw new DuplicateAccountException("User authentication failed in Security Context: " + feed.getFeedId());
        } catch (Exception e) {
            log.error("Failed to create user: " + e.getMessage());
            throw new ResourceNotFoundException("Failed to create feed with feedId: " + feed.getFeedId());
        }
    }
    
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Feed update(Feed feed) {
        try {
            FeedEntity feedEntity = feed.toEntity();
            return Feed.from(feedRepository.save(feedEntity));
        } catch (Exception e) {
            log.error("Failed to update feed: " + e.getMessage());
            throw new ResourceNotFoundException("Failed to update feed with feedId: " + feed.getFeedId());
        }
    }
    
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Feed delete(String feedId) {
        try {
            feedRepository.delete(feedId);
            return Feed.builder().feedId(feedId).build();
        } catch (Exception e) {
            log.error("Failed to delete feed: " + e.getMessage());
            throw new ResourceNotFoundException("Failed to delete feed with feedId: " + feedId);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Feed getById(String feedId) {
        try {
            FeedEntity feedEntity = feedRepository.findById(feedId)
                    .orElseThrow(() -> new NoResultException("No feed found with feedId: " + feedId));
            
            return Feed.from(feedEntity);
        } catch (Exception e) {
            log.error("Failed to fetch feed: " + e.getMessage());
            throw new ResourceNotFoundException("Failed to fetch feed with feedId: " + feedId);
        }
    }
}
