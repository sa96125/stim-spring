package com.sa96125.stim.common.api.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HttpUtils {
    
    private static final String UNKNOWN_IP = "unknown";
    
    public static String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || UNKNOWN_IP.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || UNKNOWN_IP.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || UNKNOWN_IP.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
    
    
    public static Map<String, String> extractHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        return headers;
    }
    
    public static Map<String, String> extractParameters(HttpServletRequest request) {
        Map<String, String> parameters = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            parameters.put(paramName, request.getParameter(paramName));
        }
        return parameters;
    }
    
    public static HttpHeaders createHttpHeaders(String headerName, String headerValue) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(headerName, headerValue);
        return headers;
    }
    
    public static String extractRequestBody(ContentCachingRequestWrapper request) throws IOException {
        if (request != null) {
            byte[] buf = request.getContentAsByteArray();
            if (buf.length > 0) {
                return new String(buf, request.getCharacterEncoding());
            }
        }
        return null;
    }
    
    public static Cookie createCookie(String cookieName, String cookieValue, int cookieExpiration) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(cookieExpiration);
        cookie.setPath("/");
        return cookie;
    }
}
