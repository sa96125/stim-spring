package com.sa96125.stim.domain.comment.repository;

import com.sa96125.stim.domain.comment.repository.port.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {
    
    private final CommentJpaRepository commentJpaRepository;
    
    @Override
    public CommentEntity save(CommentEntity commentEntity) {
        return commentJpaRepository.save(commentEntity);
    }
    
    @Override
    public CommentEntity update(CommentEntity commentEntity) {
        return commentJpaRepository.save(commentEntity);
    }
    
    @Override
    public void delete(String commentId) {
        commentJpaRepository.deleteByCommentId(commentId);
    }
    
    @Override
    public Optional<CommentEntity> findById(String commentId) {
        return commentJpaRepository.findByCommentId(commentId);
    }
}
