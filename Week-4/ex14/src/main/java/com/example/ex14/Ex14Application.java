package com.example.ex14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Ex14Application {

	public static void main(String[] args) {
		SpringApplication.run(Ex14Application.class, args);
	}

}
