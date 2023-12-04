package com.sa96125.stim.domain.user.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sa96125.stim.common.api.type.Role;
import com.sa96125.stim.common.api.type.Status;
import com.sa96125.stim.domain.user.service.User;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestUpdate {
    
    private final String password;
    private final String nick;
    private final String name;
    private final String mobile;
    private final Role role;
    private final String status;
    
    public RequestUpdate(
            @JsonProperty("password") String password,
            @JsonProperty("nick") String nick,
            @JsonProperty("name") String name,
            @JsonProperty("mobile") String mobile,
            @JsonProperty("role") Role role,
            @JsonProperty("status") String status) {
        this.password = password;
        this.nick = nick;
        this.name = name;
        this.mobile = mobile;
        this.role = role;
        this.status = status;
    }
    
    public User toUser(String id) {
        return User.builder()
                .userId(id)
                .password(this.getPassword())
                .nick(this.getNick())
                .name(this.name)
                .mobile(this.mobile)
                .role(this.role)
                .status(Status.PENDING.getValue())
                .build();
    }
}
