package com.example.ex5;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Persona {
    private String nombre;
    private String ciudad;
    private int edad;

    public Persona(){
        this.nombre = null;
        this.ciudad = null;
        this.edad = -1;

    }
    public Persona(String nombre, String ciudad, int edad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.edad = edad;
    }

}