package com.sa96125.stim.common.api.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class LogInfo {
    
    private String name;
    private String url;
    private String method;
    private Map<String, String> headers;
    private Map<String, String> parameters;
    private Object body;
    private int statusCode;
    private String ipAddress;
    private String userId;
    private String userName;
    private long executionTime;
}
