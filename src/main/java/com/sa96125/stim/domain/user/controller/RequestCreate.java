package com.sa96125.stim.domain.user.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sa96125.stim.common.api.type.Status;
import com.sa96125.stim.domain.user.service.User;
import lombok.Getter;

import java.util.UUID;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestCreate {
    
    private final String email;
    private final String password;
    
    public RequestCreate(
            @JsonProperty("email") String email,
            @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }
    
    public User toUser() {
        return User.builder()
                .userId(UUID.randomUUID().toString())
                .email(this.getEmail())
                .password(this.getPassword())
                .status(Status.PENDING.getValue())
                .build();
    }
}