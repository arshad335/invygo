package com.example.invygo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class InvygoApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvygoApplication.class, args);
	}

}
