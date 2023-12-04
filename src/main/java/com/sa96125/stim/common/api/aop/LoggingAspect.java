package com.sa96125.stim.common.api.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa96125.stim.common.api.log.LogInfo;
import com.sa96125.stim.common.api.util.HttpUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LoggingException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class LoggingAspect {
    
    private final ObjectMapper objectMapper;
    
    @Pointcut("within(*..*Controller) && !@annotation(com.sa96125.stim.common.api.log.NotLogging)")
    public void onRequest() {
        // This method is intentionally empty. It serves as a pointcut declaration for logging aspects.
        // The actual pointcut expression is specified in the annotation.
    }
    
    @Around("onRequest()")
    public Object requestLogging(ProceedingJoinPoint joinPoint) throws LoggingException, IOException {
        long startTime = System.currentTimeMillis();
        Object result = null;
        
        try {
            result = joinPoint.proceed();
            return result;
        } catch (Throwable e) {
            log.error("Exception occurred during method execution: {}", String.valueOf(e));
            throw new LoggingException(e.getMessage());
        } finally {
            long executionTime = System.currentTimeMillis() - startTime;
            LogInfo logInfo = createLogInfo(joinPoint, executionTime);
            log.info(objectMapper.writeValueAsString(Map.of("logInfo", logInfo)));
        }
    }
    
    private LogInfo createLogInfo(ProceedingJoinPoint joinPoint, long executionTime) throws IOException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        
        if (attributes == null) {
            log.info("No request attributes available");
        }
        
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        
        String methodName = joinPoint.getSignature().getName();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        Map<String, String> headers = HttpUtils.extractHeaders(request);
        Map<String, String> parameters = HttpUtils.extractParameters(request);
        String body = HttpUtils.extractRequestBody(WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class));
        int statusCode = Objects.requireNonNull(attributes.getResponse()).getStatus();
        String ipAddress = HttpUtils.getClientIp(request);
        String userId = "none";
        String userName = "none";
        
        return new LogInfo(methodName, url, method, headers, parameters, body, statusCode, ipAddress, userId, userName, executionTime);
    }
    
}
