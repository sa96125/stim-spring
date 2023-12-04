package com.sa96125.stim.domain.auth.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestLogin {
    
    private final String email;
    private final String password;
    
    @Builder
    public RequestLogin(@JsonProperty("email") String email,
                        @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }
}