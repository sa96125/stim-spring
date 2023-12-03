package com.sa96125.stim.domain.user.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestUpdate {
    
    private final String password;
    private final String nick;
    private final String name;
    private final String mobile;
    private final String role;
    private final String status;
    
    @Builder
    public RequestUpdate(
            @JsonProperty("password") String password,
            @JsonProperty("nick") String nick,
            @JsonProperty("name") String name,
            @JsonProperty("mobile") String mobile,
            @JsonProperty("role") String role,
            @JsonProperty("status") String status) {
        this.password = password;
        this.nick = nick;
        this.name = name;
        this.mobile = mobile;
        this.role = role;
        this.status = status;
    }
}
