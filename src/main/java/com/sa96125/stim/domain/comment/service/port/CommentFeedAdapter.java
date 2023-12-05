package com.sa96125.stim.domain.comment.service.port;

import com.sa96125.stim.domain.feed.repository.FeedEntity;

import java.util.Optional;

public interface CommentFeedAdapter {
    Optional<FeedEntity> findById(String feedId);
}
