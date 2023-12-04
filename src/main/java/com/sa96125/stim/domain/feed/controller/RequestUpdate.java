package com.sa96125.stim.domain.feed.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sa96125.stim.common.api.type.Status;
import com.sa96125.stim.domain.feed.service.Feed;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestUpdate {
    
    private final String feedId;
    private final String title;
    private final String content;
    private final Status status;
    
    public RequestUpdate(
            @JsonProperty("feedId") String feedId,
            @JsonProperty("title") String title,
            @JsonProperty("content") String content,
            @JsonProperty("status") Status status) {
        this.feedId = feedId;
        this.title = title;
        this.content = content;
        this.status = status;
    }
    
    public Feed toFeed(String feedId) {
        return Feed.builder()
                .feedId(feedId)
                .title(this.getTitle())
                .content(this.getContent())
                .status(Status.ACTIVE)
                .build();
    }
}
