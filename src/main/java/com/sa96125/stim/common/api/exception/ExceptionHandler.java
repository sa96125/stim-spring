package com.sa96125.stim.common.api.exception;

import com.sa96125.stim.common.api.exception.custom.AuthenticationFailedException;
import com.sa96125.stim.common.api.exception.custom.DuplicateAccountException;
import com.sa96125.stim.common.api.exception.custom.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler {
    
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @org.springframework.web.bind.annotation.ExceptionHandler(AuthenticationFailedException.class)
    public ResponseError handleAuthenticationFailedException(AuthenticationFailedException exception) {
        log.info("AuthenticationFailedException: " + exception.getMessage());
        return ResponseError.of(exception.getMessage());
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseError handleResourceNotFoundException(ResourceNotFoundException exception) {
        log.info("ResourceNotFoundException: " + exception.getMessage());
        return ResponseError.of(exception.getMessage());
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateAccountException.class)
    public ResponseError handleDuplicateAccountException(DuplicateAccountException exception) {
        log.info("DuplicateAccountException: " + exception.getMessage());
        return ResponseError.of(exception.getMessage());
    }
}
