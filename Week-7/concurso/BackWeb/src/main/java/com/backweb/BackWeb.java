package com.backweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication @EnableEurekaClient
public class BackWeb {

	public static void main(String[] args) {
		SpringApplication.run(BackWeb.class, args);
	}

}
