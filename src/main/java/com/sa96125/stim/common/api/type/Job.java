package com.sa96125.stim.common.api.type;

import lombok.Getter;

@Getter
public enum Job {

    NEW_NOTICE("NEW_NOTICE"),
    NEW_FEED("NEW_FEED"),
    NEW_COMMENT("NEW_COMMENT"),
    NEW_FOLLOWER("NEW_FOLLOWER");

    private final String value;

    Job(String value) {
        this.value = value;
    }
}