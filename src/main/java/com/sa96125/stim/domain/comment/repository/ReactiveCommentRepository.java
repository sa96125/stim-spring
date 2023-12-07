package com.sa96125.stim.domain.comment.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ReactiveCommentRepository extends ReactiveCrudRepository<CommentEntity, Long> {
    Mono<Void> deleteByCommentId(String commentId);

    Mono<CommentEntity> findByCommentId(String commentId);
}