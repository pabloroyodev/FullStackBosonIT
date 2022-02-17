package com.example.ex6;

import com.example.ex6.Domain.PersonaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class Ex6Application {

	public static void main(String[] args) {
		SpringApplication.run(Ex6Application.class, args);
	}

	@Bean
	ArrayList<PersonaService> getPersonas() {
		ArrayList<PersonaService> personas = new ArrayList<>();
		return personas;
	}

}