package com.sa96125.stim.domain.auth.controller;

import com.sa96125.stim.common.api.util.HttpUtils;
import com.sa96125.stim.common.api.vo.ResponseMessage;
import com.sa96125.stim.common.redis.port.SessionAdapter;
import com.sa96125.stim.common.twilio.port.SmsAdapter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "인증 (auth)")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private static final String ACCESS_KEY_HEADER = "Mobile-Access-Token";
    private final SmsAdapter smsAdapter;
    private final SessionAdapter sessionAdapter;
    
    @PostMapping("/login")
    public ResponseEntity<ResponseMessage> login(@RequestBody RequestLogin request) {
        return ResponseEntity.ok().body(ResponseMessage.createSentCodeSuccessResponse());
    }
    
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    public ResponseEntity<ResponseMessage> logout() {
        return ResponseEntity.ok().body(ResponseMessage.createSentCodeSuccessResponse());
    }
    
    @PutMapping("/send-sms")
    public ResponseEntity<ResponseMessage> sendSMS(@RequestBody RequestSMS request) {
        String verificationCode = smsAdapter.sendVerificationCode(request.getMobile());
        String token = sessionAdapter.saveVerificationCodeFor3Minutes(verificationCode);
        HttpHeaders headers = HttpUtils.createHttpHeaders(ACCESS_KEY_HEADER, token);
        return ResponseEntity.ok().headers(headers).body(ResponseMessage.createSentCodeSuccessResponse());
    }
    
    @PutMapping("/verify-mobile")
    public ResponseEntity<ResponseMessage> verifyMobile(@RequestBody RequestMobileAuth request) {
        sessionAdapter.validateMobile(request.getToken(), request.getVerificationCode());
        return ResponseEntity.ok().body(ResponseMessage.createVerificationSuccessMessage());
    }
}
