package com.example.ex4;

import Persona.Persona;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class Ex4Application {

	public static void main(String[] args) {
		SpringApplication.run(Ex4Application.class, args);
	}

	//MODO 1: http://localhost:8080/user/pablo
	@GetMapping("/user")
	public String Hola(
			@RequestParam(value = "nombre", required = false) String nombre) {
		return "Hola " + nombre;
	}

	//MODO 2: http://localhost:8080/user?nombre=pablo
	@GetMapping("/user/{nombre}")
	public String HolaSlash(@PathVariable String nombre) {
		return "Hola " + nombre;
	}

	@PostMapping("/useradd")
	public String registerNewUser(@RequestBody Persona persona) {
		persona.setEdad(persona.getEdad()+1);
		return persona.toString();
	}

}

