package com.sa96125.stim.common.api.type;

import lombok.Getter;

@Getter
public enum Notify {
    
    NEW_GENERAL("NEW_GENERAL"),
    NEW_FEED("NEW_FEED"),
    NEW_COMMENT("NEW_COMMENT"),
    NEW_FOLLOW("NEW_FOLLOW"),
    NEW_FOLLOWER("NEW_FOLLOWER");
    
    private final String value;
    
    Notify(String value) {
        this.value = value;
    }
}