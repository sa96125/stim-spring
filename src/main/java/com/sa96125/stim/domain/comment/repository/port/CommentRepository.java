package com.sa96125.stim.domain.comment.repository.port;

import com.sa96125.stim.domain.comment.repository.CommentEntity;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface CommentRepository {
    Mono<CommentEntity> save(CommentEntity commentEntity);

    Mono<CommentEntity> update(CommentEntity commentEntity);

    Mono<Void> delete(String commentId);

    Mono<CommentEntity> findById(String commentId);
}

