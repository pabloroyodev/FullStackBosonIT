package com.example.ex16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Ex16Application {

	public static void main(String[] args) {
		SpringApplication.run(Ex16Application.class, args);
	}

}
