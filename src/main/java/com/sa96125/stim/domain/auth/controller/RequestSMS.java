package com.sa96125.stim.domain.auth.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestSMS {
    private final String mobile;
    
    @Builder
    public RequestSMS(@JsonProperty("mobile") String mobile) {
        this.mobile = mobile;
    }
}