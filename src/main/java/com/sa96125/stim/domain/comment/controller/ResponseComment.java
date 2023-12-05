package com.sa96125.stim.domain.comment.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sa96125.stim.common.api.type.Status;
import com.sa96125.stim.domain.comment.service.Comment;
import com.sa96125.stim.domain.feed.controller.ResponseFeed;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseComment {
    
    private final String commentId;
    private final String content;
    private final Status status;
    private final ResponseFeed responseFeed;
    private final ResponseComment responseParentComment;
    private final List<ResponseComment> responseChildComments;
    
    public static ResponseComment from(Comment c) {
        return ResponseComment.builder()
                .commentId(c.getCommentId())
                .content(c.getContent())
                .status(c.getStatus())
                .responseFeed(ResponseFeed.from(c.getFeed()))
                .responseParentComment(c.getParentComment() != null ? ResponseComment.from(c.getParentComment()) : null)
                .responseChildComments(c.getChildComments() != null ? c.getChildComments().stream().map(ResponseComment::from).toList() : null)
                .build();
    }
}
