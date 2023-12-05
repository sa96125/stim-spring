package com.sa96125.stim.domain.comment.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sa96125.stim.common.api.type.Status;
import com.sa96125.stim.domain.comment.service.Comment;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestUpdate {
    
    private final String commentId;
    private final String content;
    private final Status status;
    
    public RequestUpdate(@JsonProperty("commentId") String commentId,
                         @JsonProperty("content") String content,
                         @JsonProperty("status") Status status) {
        this.commentId = commentId;
        this.content = content;
        this.status = status;
    }
    
    public Comment toComment(String commentId) {
        return Comment.builder()
                .commentId(commentId)
                .content(this.getContent())
                .status(this.status)
                .build();
    }
}
