package com.sa96125.stim.common.api.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HttpUtils {
    
    public static HttpHeaders createHttpHeaders(String headerName, String headerValue) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(headerName, headerValue);
        return headers;
    }
}
