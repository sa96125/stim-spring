package com.sa96125.stim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestStimApplication {
    
    public static void main(String[] args) {
        SpringApplication.from(StimApplication::main).with(TestStimApplication.class).run(args);
    }
    
}
