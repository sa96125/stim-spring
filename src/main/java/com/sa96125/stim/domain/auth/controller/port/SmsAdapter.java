package com.sa96125.stim.domain.auth.controller.port;

public interface SmsAdapter {
    String sendVerificationCode(String mobile);
}
