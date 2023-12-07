package com.sa96125.stim.domain.comment.service;

import com.sa96125.stim.common.api.type.Status;
import com.sa96125.stim.domain.comment.repository.CommentEntity;
import com.sa96125.stim.domain.feed.service.Feed;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Comment {
    
    private String commentId;
    private String content;
    private String feedId;
    private Feed feed;
    private Status status;
    private String parentCommentId;
    private Comment parentComment;
    private List<Comment> childComments;
    
    
    public static Comment from(CommentEntity e) {
        return Comment.builder()
                .commentId(e.getCommentId())
                .content(e.getContent())
                .feedId(e.getFeed().getFeedId())
                .feed(Feed.from(e.getFeed()))
                .status(e.getStatus())
                .parentCommentId(e.getParentComment().getCommentId())
                .parentComment(e.getParentComment() != null ? Comment.from(e.getParentComment()) : null)
                .childComments(e.getChildComments() != null ? e.getChildComments().stream().map(Comment::from).toList() : null)
                .build();
    }
    
    public CommentEntity toEntity() {
        CommentEntity e = new CommentEntity();
        e.setCommentId(this.commentId);
        e.setContent(this.content);
        e.setFeed(this.feed.toEntity());
        e.setParentComment(this.parentComment.toEntity());
        e.setChildComments(this.childComments.stream().map((Comment::toEntity)).toList());
        e.setStatus(this.getStatus());
        return e;
    }
}
