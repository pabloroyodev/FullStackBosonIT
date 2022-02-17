package com.example.ex6.Domain;

import org.springframework.stereotype.Service;

@Service("PersonaServiceImpl")
public class PersonaServiceImpl implements PersonaService {
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

    public String getEdad() {
        return persona.getEdad();
    }

    public void setEdad(String edad) {
        persona.setEdad(edad);
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona.setId(persona.getId());
        this.persona.setNombre(persona.getNombre());
        this.persona.setCiudad(persona.getCiudad());
        this.persona.setEdad(persona.getEdad());
    }

    public Persona crearPersona(int id, String nombre, String ciudad, String edad) {
        persona.setNombre(nombre);
        persona.setCiudad(ciudad);
        persona.setEdad(edad);
        return persona;
    }

    public Persona crearPersona(int id, Persona persona) {
        this.persona.setId(id);
        this.persona.setNombre(persona.getNombre());
        this.persona.setCiudad(persona.getCiudad());
        this.persona.setEdad(persona.getEdad());
        return persona;
    }
}