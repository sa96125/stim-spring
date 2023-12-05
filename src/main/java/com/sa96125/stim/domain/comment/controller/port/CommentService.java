package com.sa96125.stim.domain.comment.controller.port;

import com.sa96125.stim.domain.comment.service.Comment;

public interface CommentService {
    Comment create(Comment comment);
    
    Comment update(Comment comment);
    
    Comment delete(String commentId);
    
    Comment getById(String commentId);
}
