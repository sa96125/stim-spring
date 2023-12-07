package com.sa96125.stim.domain.comment.repository;

import com.sa96125.stim.domain.comment.repository.port.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
    
    private final ReactiveCommentRepository reactiveCommentRepository;

    @Override
    public Mono<CommentEntity> save(CommentEntity commentEntity) {
        return reactiveCommentRepository.save(commentEntity);
    }

    @Override
    public Mono<CommentEntity> update(CommentEntity commentEntity) {
        return reactiveCommentRepository.save(commentEntity);
    }

    @Override
    public Mono<Void> delete(String commentId) {
        return reactiveCommentRepository.deleteByCommentId(commentId);
    }

    @Override
    public Mono<CommentEntity> findById(String commentId) {
        return reactiveCommentRepository.findByCommentId(commentId);
    }
}
