package com.sa96125.stim.domain.comment.repository;

import org.springframework.data.repository.CrudRepository;

public interface JpaCommentRepository extends CrudRepository<CommentEntity, Long> {
    void deleteByCommentId(String commentId);

    CommentEntity findByCommentId(String commentId);
}
