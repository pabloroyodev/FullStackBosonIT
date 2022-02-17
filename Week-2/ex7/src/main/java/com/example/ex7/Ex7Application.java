package com.example.ex7;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Ex7Application {

	public static void main(String[] args) {
		SpringApplication.run(Ex7Application.class, args);
	}

	@PostConstruct
	public void primera() {
		System.out.println("Hola desde la clase inicial");
	}

	@Bean
	public CommandLineRunner segunda() throws Exception {
		return args -> {
			System.out.println("Hola desde la clase secundaria");
		};
	}

	@Bean
	public void tercera() throws Exception {
		Clase3 ct = new Clase3();

		ct.run("Esta es la clase numero 3 y no entido porque sale en el medio");
	}
}
