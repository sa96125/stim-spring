package com.sa96125.stim.domain.comment.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentJpaRepository extends CrudRepository<CommentEntity, Long> {
    void deleteByCommentId(String commentId);
    
    Optional<CommentEntity> findByCommentId(String commentId);
}
