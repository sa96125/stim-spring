package com.sa96125.stim.domain.comment.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ReactiveCommentRepository extends ReactiveCrudRepository<CommentEntity, Long> {
}