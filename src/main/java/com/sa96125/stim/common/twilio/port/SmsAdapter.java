package com.sa96125.stim.common.twilio.port;

public interface SmsAdapter {
    String sendVerificationCode(String mobile);
}
