package com.sa96125.stim.common.api.vo;

import lombok.Builder;

@Builder
public class ResponseMessage {
    
    private final String message;
    
    private ResponseMessage(String message) {
        this.message = message;
    }
    
    public static ResponseMessage of(String message) {
        return new ResponseMessage(message);
    }
    
    public static ResponseMessage createVerificationSuccessMessage() {
        return of("Successfully verified mobile number.");
    }
    
    public static ResponseMessage createSentCodeSuccessResponse() {
        return of("Successfully sent verification code.");
    }
}
