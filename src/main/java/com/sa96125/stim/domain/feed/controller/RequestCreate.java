package com.sa96125.stim.domain.feed.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sa96125.stim.common.api.type.Status;
import com.sa96125.stim.domain.feed.service.Feed;
import lombok.Getter;

import java.util.UUID;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestCreate {
    
    private final String userId;
    private final String title;
    private final String content;
    private final Status status;
    
    public RequestCreate(
            @JsonProperty("userId") String userId,
            @JsonProperty("title") String title,
            @JsonProperty("content") String content,
            @JsonProperty("status") Status status) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.status = status;
    }
    
    public Feed toFeed() {
        return Feed.builder()
                .feedId(UUID.randomUUID().toString())
                .userId(this.getUserId())
                .title(this.getTitle())
                .content(this.getContent())
                .status(Status.ACTIVE)
                .build();
    }
}