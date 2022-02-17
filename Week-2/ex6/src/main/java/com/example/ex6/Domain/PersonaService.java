package com.example.ex6.Domain;

public interface PersonaService {
    public Persona crearPersona(int id, String nombre, String ciudad, String edad);

    public Persona crearPersona(int id, Persona persona);

    public String getNombre();

    public void setNombre(String nombre);

    public String getCiudad();

    public void setCiudad(String ciudad);

    public String getEdad();

    public void setEdad(String edad);

    public Persona getPersona();

    public void setPersona(Persona persona);
}