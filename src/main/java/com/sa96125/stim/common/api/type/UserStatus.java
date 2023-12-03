package com.sa96125.stim.common.api.type;

import lombok.Getter;

@Getter
public enum UserStatus {
    
    ACTIVE("ACTIVE"),
    PENDING("PENDING"),
    SUSPENDED("SUSPENDED");
    
    private final String value;
    
    UserStatus(String value) {
        this.value = value;
    }
}