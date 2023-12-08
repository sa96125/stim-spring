package com.sa96125.stim.domain.comment.repository;

import com.sa96125.stim.domain.comment.repository.port.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final JpaCommentRepository jpaCommentRepository;
    private final ReactiveCommentRepository reactiveCommentRepository;

    @Override
    public CommentEntity save(CommentEntity commentEntity) {
        return jpaCommentRepository.save(commentEntity);
    }

    @Override
    public CommentEntity update(CommentEntity commentEntity) {
        return jpaCommentRepository.save(commentEntity);
    }

    @Override
    public void delete(String commentId) {
        jpaCommentRepository.deleteByCommentId(commentId);
    }

    @Override
    public CommentEntity findById(String commentId) {
        return jpaCommentRepository.findByCommentId(commentId);
    }
}
