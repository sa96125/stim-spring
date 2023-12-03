package com.sa96125.stim.common.api.exception;

import lombok.Getter;

@Getter
public class ResponseError {
    
    private final String error;
    
    private ResponseError(String error) {
        this.error = error;
    }
    
    public static ResponseError of(String error) {
        return new ResponseError(error);
    }
}