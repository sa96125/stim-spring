package com.sa96125.stim.common.api.type;

import lombok.Getter;

@Getter
public enum Status {
    
    ACTIVE("ACTIVE"),
    PENDING("PENDING"),
    SUSPENDED("SUSPENDED");
    
    private final String value;
    
    Status(String value) {
        this.value = value;
    }
}