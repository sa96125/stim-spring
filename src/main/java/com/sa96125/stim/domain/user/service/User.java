package com.sa96125.stim.domain.user.service;

import com.sa96125.stim.common.api.type.Role;
import com.sa96125.stim.common.api.type.Status;
import com.sa96125.stim.domain.feed.service.Feed;
import com.sa96125.stim.domain.user.repository.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class User {
    
    private final String userId;
    private final String email;
    private final String password;
    private final String nick;
    private final String name;
    private final String mobile;
    private final Role role;
    private final Status status;
    private final String verificationCode;
    private final List<Feed> feeds;
    private final long lastLoginAt;
    
    public static User from(UserEntity e) {
        return User.builder()
                .userId(e.getUserId())
                .email(e.getEmail())
                .nick(e.getNick())
                .name(e.getName())
                .mobile(e.getMobile())
                .role(e.getRole())
                .status(e.getStatus())
                .feeds(e.getFeeds().stream().map(Feed::from).toList())
                .lastLoginAt(e.getLastLoginAt())
                .build();
    }
    
    public UserEntity toEntity() {
        UserEntity e = new UserEntity();
        e.setUserId(this.userId);
        e.setEmail(this.email);
        e.setNick(this.nick);
        e.setName(this.name);
        e.setMobile(this.mobile);
        e.setRole(this.role);
        e.setStatus(this.status);
        e.setFeeds(this.feeds.stream().map(Feed::toEntity).toList());
        e.setLastLoginAt(this.lastLoginAt);
        return e;
    }
}
