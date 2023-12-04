package com.sa96125.stim.common.redis;

import com.sa96125.stim.common.api.exception.custom.ResourceNotFoundException;
import com.sa96125.stim.domain.auth.controller.port.SessionAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisClient implements SessionAdapter {
    
    private static final String VERIFICATION_CODE_KEY_PREFIX = "verificationCode-";
    private final StringRedisTemplate stringRedisTemplate;
    
    @Override
    public String saveVerificationCodeFor3Minutes(String verificationCode) {
        String accessKey = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(VERIFICATION_CODE_KEY_PREFIX + accessKey, verificationCode, 3, TimeUnit.MINUTES);
        return accessKey;
    }
    
    @Override
    public void validateMobile(String key, String verificationCode) {
        if (!Objects.equals(stringRedisTemplate.opsForValue().get(VERIFICATION_CODE_KEY_PREFIX + key), verificationCode)) {
            throw new ResourceNotFoundException("Mobile validation failed: Verification code does not match");
        }
    }
}
