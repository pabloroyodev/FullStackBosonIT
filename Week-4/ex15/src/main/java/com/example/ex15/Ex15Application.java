package com.example.ex15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Ex15Application {

	public static void main(String[] args) {
		SpringApplication.run(Ex15Application.class, args);
	}

}
