package com.sa96125.stim.domain.auth.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestMobileAuth {
    
    private final String token;
    private final String verificationCode;
    
    @Builder
    public RequestMobileAuth(@JsonProperty("token") String token,@JsonProperty("verificationCode") String verificationCode) {
        this.token = token;
        this.verificationCode = verificationCode;
    }
}
