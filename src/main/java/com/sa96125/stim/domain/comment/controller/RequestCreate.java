package com.sa96125.stim.domain.comment.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sa96125.stim.common.api.type.Status;
import com.sa96125.stim.domain.comment.service.Comment;
import lombok.Getter;

import java.util.UUID;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestCreate {
    
    private final String feedId;
    private final String parentCommentId;
    private final String content;
    private final Status status;
    
    public RequestCreate(@JsonProperty("feedId") String feedId,
                         @JsonProperty("parentCommentId") String parentCommentId,
                         @JsonProperty("content") String content,
                         @JsonProperty("status") Status status) {
        this.feedId = feedId;
        this.parentCommentId = parentCommentId;
        this.content = content;
        this.status = status;
    }
    
    public Comment toComment() {
        return Comment.builder()
                .feedId(this.feedId)
                .prentCommentId(this.parentCommentId)
                .commentId(UUID.randomUUID().toString())
                .content(this.getContent())
                .status(Status.ACTIVE)
                .build();
    }
}