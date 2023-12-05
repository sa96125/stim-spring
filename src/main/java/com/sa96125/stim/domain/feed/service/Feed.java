package com.sa96125.stim.domain.feed.service;

import com.sa96125.stim.common.api.type.Status;
import com.sa96125.stim.domain.feed.repository.FeedEntity;
import com.sa96125.stim.domain.user.service.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Feed {
    
    private String feedId;
    private String title;
    private String content;
    private Status status;
    private User user;
    
    public static Feed from(FeedEntity e) {
        return Feed.builder()
                .feedId(e.getFeedId())
                .title(e.getTitle())
                .content(e.getContent())
                .status(e.getStatus())
                .user(User.from(e.getUser()))
                .build();
    }
    
    public FeedEntity toEntity() {
        FeedEntity e = new FeedEntity();
        e.setFeedId(this.feedId);
        e.setTitle(this.title);
        e.setContent(this.content);
        e.setUser(this.user.toEntity());
        e.setStatus(this.getStatus());
        return e;
    }
}
