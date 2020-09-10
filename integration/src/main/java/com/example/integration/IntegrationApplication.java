package com.example.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntegrationApplication {

	public static void main(String[] args) {
		System.setProperty("es.set.netty.runtime.available.processors","false");
		SpringApplication.run(IntegrationApplication.class, args);
	}

}
