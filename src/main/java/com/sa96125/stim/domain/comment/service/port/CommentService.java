package com.sa96125.stim.domain.comment.service.port;

import com.sa96125.stim.domain.comment.service.Comment;
import reactor.core.publisher.Mono;

public interface CommentService {
    Mono<Comment> create(Comment comment);

    Mono<Comment> update(Comment comment);

    Mono<Comment> delete(String commentId);

    Mono<Comment> getById(String commentId);
}
