package com.economizei.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EconomizeiApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EconomizeiApiApplication.class, args);
	}

}
