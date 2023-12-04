package com.sa96125.stim.domain.feed.service;

import com.sa96125.stim.common.api.type.Status;
import com.sa96125.stim.domain.feed.repository.FeedEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Feed {
    
    private String feedId;
    private String userId;
    private String title;
    private String content;
    private Status status;
    
    public static Feed from(FeedEntity e) {
        return Feed.builder()
                .feedId(e.getFeedId())
                .userId(e.getUserId())
                .title(e.getTitle())
                .content(e.getContent())
                .status(e.getStatus())
                .build();
    }
    
    public FeedEntity toEntity() {
        FeedEntity e = new FeedEntity();
        e.setFeedId(this.feedId);
        e.setUserId(this.userId);
        e.setTitle(this.title);
        e.setContent(this.content);
        e.setStatus(this.getStatus());
        return e;
    }
}
