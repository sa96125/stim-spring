package com.sa96125.stim.domain.comment.service;

import com.sa96125.stim.common.api.exception.custom.ResourceNotFoundException;
import com.sa96125.stim.domain.comment.controller.port.CommentService;
import com.sa96125.stim.domain.comment.repository.CommentEntity;
import com.sa96125.stim.domain.comment.service.port.CommentFeedAdapter;
import com.sa96125.stim.domain.comment.service.port.CommentRepository;
import com.sa96125.stim.domain.feed.repository.FeedEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    
    private final CommentRepository commentRepository;
    private final CommentFeedAdapter commentFeedAdapter;
    
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Comment create(Comment comment) {
        try {
            CommentEntity commentEntity = comment.toEntity();
            
            if (comment.getFeedId() != null) {
                String feedId = comment.getFeedId();
                FeedEntity feedEntity = commentFeedAdapter.findById(feedId).orElseThrow();
                commentEntity.setFeed(feedEntity);
            } else if (comment.getPrentCommentId() != null) {
                String parentCommentId = comment.getPrentCommentId();
                CommentEntity parentCommentEntity = commentRepository.findById(parentCommentId).orElseThrow();
                commentEntity.setParentComment(parentCommentEntity);
            }
            
            return Comment.from(commentRepository.save(commentEntity));
        } catch (Exception e) {
            log.error("Failed to create comment: " + e.getMessage());
            throw new ResourceNotFoundException("Failed to create comment with commentId: " + comment.getCommentId());
        }
    }
    
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Comment update(Comment comment) {
        try {
            CommentEntity commentEntity = comment.toEntity();
            return Comment.from(commentRepository.save(commentEntity));
        } catch (Exception e) {
            log.error("Failed to update comment: " + e.getMessage());
            throw new ResourceNotFoundException("Failed to update comment with commentId: " + comment.getCommentId());
        }
    }
    
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Comment delete(String commentId) {
        try {
            commentRepository.delete(commentId);
            return Comment.builder().commentId(commentId).build();
        } catch (Exception e) {
            log.error("Failed to delete comment: " + e.getMessage());
            throw new ResourceNotFoundException("Failed to delete comment with commentId: " + commentId);
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Comment getById(String commentId) {
        try {
            CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow();
            return Comment.from(commentEntity);
        } catch (Exception e) {
            log.error("Failed to fetch comment: " + e.getMessage());
            throw new ResourceNotFoundException("Failed to fetch comment with commentId: " + commentId);
        }
    }
}
