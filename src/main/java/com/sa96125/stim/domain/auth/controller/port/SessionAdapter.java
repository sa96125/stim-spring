package com.sa96125.stim.domain.auth.controller.port;

public interface SessionAdapter {
    String saveFor3Minutes(String verificationCode);
    void validateMobile(String key, String verificationCode);
}
