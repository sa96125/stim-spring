package com.sa96125.stim.domain.comment.service;

import com.sa96125.stim.common.api.exception.custom.ResourceNotFoundException;
import com.sa96125.stim.domain.comment.repository.CommentEntity;
import com.sa96125.stim.domain.comment.repository.port.CommentRepository;
import com.sa96125.stim.domain.comment.service.port.CommentService;
import com.sa96125.stim.domain.feed.repository.port.FeedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final FeedRepository feedRepository;

    @Override
    public Mono<Comment> create(Comment comment) {
        return processCommentEntity(comment)
                .publishOn(Schedulers.boundedElastic())
                .flatMap(commentRepository::save)
                .map(Comment::from)
                .onErrorMap(e -> handleCommentError("Failed to create", comment.getCommentId(), e));
    }

    @Override
    public Mono<Comment> update(Comment comment) {
        return Mono.just(comment.toEntity())
                .flatMap(commentRepository::save)
                .map(Comment::from)
                .publishOn(Schedulers.boundedElastic())
                .onErrorMap(e -> handleCommentError("Failed to update", comment.getCommentId(), e));
    }

    @Override
    public Mono<Comment> delete(String commentId) {
        return Mono.defer(() -> commentRepository.delete(commentId)
                        .thenReturn(Comment.builder().commentId(commentId).build()))
                .onErrorMap(e -> new ResourceNotFoundException("Failed to delete comment with commentId: " + commentId));
    }

    @Override
    public Mono<Comment> getById(String commentId) {
        return commentRepository.findById(commentId)
                .map(Comment::from)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Comment not found with commentId: " + commentId)))
                .onErrorMap(e -> new ResourceNotFoundException("Failed to fetch comment with commentId: " + commentId));
    }

    private Throwable handleCommentError(String action, String commentId, Throwable e) {
        log.error("{} comment: {}", action, e.getMessage());
        return new ResourceNotFoundException(String.format("%s comment with commentId: %s", action, commentId));
    }

    private Mono<CommentEntity> processCommentEntity(Comment comment) {
        CommentEntity commentEntity = comment.toEntity();

        if (comment.getParentCommentId() != null) {
            return commentRepository.findById(comment.getParentCommentId())
                    .map(parentComment -> {
                        commentEntity.setParentComment(parentComment);
                        return commentEntity;
                    });
        }

        feedRepository.findById(comment.getFeedId()).ifPresent(commentEntity::setFeed);
        return Mono.just(commentEntity);
    }
}
