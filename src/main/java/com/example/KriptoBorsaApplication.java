package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity // Spring Boot 3+
public class KriptoBorsaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KriptoBorsaApplication.class, args);
	}

}
