package com.example.ex5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;


@SpringBootApplication
public class Ex5Application {

	public static void main(String[] args) {
		SpringApplication.run(Ex5Application.class, args);
	}

	@Bean
	ArrayList<CiudadService> getCiudades() {
		ArrayList<CiudadService> ciudades = new ArrayList<>();
		return ciudades;
	}

	@Bean
	@Qualifier("bean1")
	PersonaService PersonaBean1(){
		PersonaService PersonaBean1 = new PersonaServiceImpl();
		PersonaBean1.crearPersona("bean1", "Tudela", 21);
		return PersonaBean1;
	}

	@Bean
	@Qualifier("bean2")
	PersonaService PersonaBean2(){
		PersonaService PersonaBean2=new PersonaServiceImpl();
		PersonaBean2.crearPersona("bean2", "Pamplona", 23);
		return PersonaBean2;
	}

	@Bean
	@Qualifier("bean3")
	PersonaService PersonaBean3(){
		PersonaService PersonaBean3=new PersonaServiceImpl();
		PersonaBean3.crearPersona("bean3", "Tafalla", 20);
		return PersonaBean3;
	}
}
