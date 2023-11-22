package com.sa96125.stimspring;

import com.sa96125.stim.Stim;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestStimSpringApplication {

	public static void main(String[] args) {
		SpringApplication.from(Stim::main).with(TestStimSpringApplication.class).run(args);
	}

}
