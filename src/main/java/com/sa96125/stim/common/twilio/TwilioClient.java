package com.sa96125.stim.common.twilio;

import com.sa96125.stim.domain.auth.controller.port.SmsAdapter;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TwilioClient implements SmsAdapter {
    
    @Value("${twilio.account-sid}")
    private String accountSid;
    
    @Value("${twilio.auth-token}")
    private String authToken;
    
    @Value("${twilio.phone-number}")
    private String fromPhoneNumber;
    
    @Override
    public String sendVerificationCode(String mobile) {
        Twilio.init(accountSid, authToken);
        
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(mobile),
                new com.twilio.type.PhoneNumber(fromPhoneNumber),
                "0000").create();
        
        return "SMS sent: " + message.getSid();
    }
}
