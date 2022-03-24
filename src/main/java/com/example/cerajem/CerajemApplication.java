package com.example.cerajem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.example.cerajem" })
public class CerajemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CerajemApplication.class, args);
	}

}
