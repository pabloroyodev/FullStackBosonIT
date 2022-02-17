package com.example.ex5;

import java.util.ArrayList;

public interface CiudadService {
    public void crearCiudad(String nombre,int numeroHabitantes);
    public void setCiudad(Ciudad ciudad);
    public Ciudad getCiudad();
    public ArrayList<Ciudad> getCiudaddes(ArrayList<CiudadService> ciudadesService);

}