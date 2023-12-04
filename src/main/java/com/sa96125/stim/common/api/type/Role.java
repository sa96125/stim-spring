package com.sa96125.stim.common.api.type;

import lombok.Getter;

@Getter
public enum Role {
    
    ADMIN("ADMIN"),
    USER("USER");
    
    private final String value;
    
    Role(String value) {
        this.value = value;
    }
}