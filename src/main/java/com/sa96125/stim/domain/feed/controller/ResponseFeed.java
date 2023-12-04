package com.sa96125.stim.domain.feed.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sa96125.stim.common.api.type.Status;
import com.sa96125.stim.domain.feed.service.Feed;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseFeed {
    
    private final String feedId;
    private final String userId;
    private final String title;
    private final String content;
    private final Status status;
    
    public static ResponseFeed from(Feed f) {
        return builder()
                .feedId(f.getFeedId())
                .userId(f.getUserId())
                .title(f.getTitle())
                .content(f.getContent())
                .status(f.getStatus())
                .build();
    }
}
