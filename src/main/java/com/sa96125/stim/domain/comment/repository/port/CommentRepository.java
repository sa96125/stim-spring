package com.sa96125.stim.domain.comment.repository.port;

import com.sa96125.stim.domain.comment.repository.CommentEntity;

public interface CommentRepository {
    CommentEntity save(CommentEntity commentEntity);

    CommentEntity update(CommentEntity commentEntity);

    void delete(String commentId);

    CommentEntity findById(String commentId);
}

