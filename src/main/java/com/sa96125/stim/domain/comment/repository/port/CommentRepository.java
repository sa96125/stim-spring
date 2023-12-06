package com.sa96125.stim.domain.comment.repository.port;

import com.sa96125.stim.domain.comment.repository.CommentEntity;

import java.util.Optional;

public interface CommentRepository {
    CommentEntity save(CommentEntity commentEntity);
    
    CommentEntity update(CommentEntity commentEntity);
    
    void delete(String commentId);
    
    Optional<CommentEntity> findById(String commentId);
}
