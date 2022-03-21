package com.example.ex25;

import com.example.ex25.application.ArchivoStorage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ex25Application {

	public static void main(String[] args) {
		SpringApplication.run(Ex25Application.class, args);
	}

	@Bean
	CommandLineRunner init(ArchivoStorage archivoStorage) {
		return (args) -> {
			archivoStorage.deleteAll();
			archivoStorage.createFolder();
		};
	}
}
