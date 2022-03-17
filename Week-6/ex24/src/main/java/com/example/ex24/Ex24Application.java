package com.example.ex24;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Ex24Application {

	public static void main(String[] args) {
		SpringApplication.run(Ex24Application.class, args);
	}

}
