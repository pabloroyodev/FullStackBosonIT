package com.example.ex27;

import com.example.ex27.Persona.Application.PersonaService;
import com.example.ex27.Persona.Infrastructure.controller.dto.input.PersonaInputDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class Ex27Application {

	public static void main(String[] args) {
		SpringApplication.run(Ex27Application.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(PersonaService personaService){
		return args -> {
			personaService.addPersona(new PersonaInputDto("AdmiPablo", "1234"
					, "Pablo", "Admin", "pablo@admin.local", "pablo@admin.local", "Tudela"
					, true, LocalDate.now(), "imgurl", LocalDate.now(), true));
		};
	}
}
