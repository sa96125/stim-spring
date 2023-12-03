package com.sa96125.stim.domain.user.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sa96125.stim.domain.user.service.User;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {
    
    private final String userId;
    private final String nick;
    private final String name;
    private final String mobile;
    private final String status;
    
    public static ResponseUser from(User user) {
        return builder()
                .userId(user.getUserId())
                .nick(user.getNick())
                .name(user.getName())
                .mobile(user.getMobile())
                .status(user.getStatus())
                .build();
    }
}
