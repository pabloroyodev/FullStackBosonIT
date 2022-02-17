package com.example.ex5;

public interface PersonaService {
    public Persona crearPersona(String nombre,String ciudad,int edad);
    public String getNombre();
    public void setNombre(String nombre);
    public String getCiudad();
    public void setCiudad(String ciudad);
    public int getEdad();
    public void setEdad(int edad);
    public Persona getPersona();
}
