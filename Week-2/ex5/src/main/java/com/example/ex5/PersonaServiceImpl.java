package com.example.ex5;

import org.springframework.stereotype.Service;

@Service("PersonaServiceImpl")
public class PersonaServiceImpl implements PersonaService{
    Persona persona = new Persona();

    public String getNombre() {
        return persona.getNombre();
    }

    public void setNombre(String nombre) {
        persona.setNombre(nombre);
    }

    public String getCiudad() {
        return persona.getCiudad();
    }

    public void setCiudad(String ciudad) {
        persona.setCiudad(ciudad);
    }

    public int getEdad() {
        return persona.getEdad();
    }

    public void setEdad(int edad) {
        persona.setEdad(edad);
    }

    @Override
    public Persona getPersona() {
        return persona;
    }

    public Persona crearPersona(String nombre,String ciudad,int edad){
        persona.setNombre(nombre);
        persona.setCiudad(ciudad);
        persona.setEdad(edad);
        return persona;
    }
}