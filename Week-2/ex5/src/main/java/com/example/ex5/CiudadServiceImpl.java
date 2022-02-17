package com.example.ex5;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CiudadServiceImpl implements CiudadService {
    Ciudad ciudad = new Ciudad();

    public void crearCiudad(String nombre, int numeroHabitantes) {
        ciudad.setNombre(nombre);
        ciudad.setNumeroHabitantes(numeroHabitantes);
    }

    @Override
    public void setCiudad(Ciudad ciudad) {
        this.ciudad.setNombre(ciudad.getNombre());
        this.ciudad.setNumeroHabitantes(ciudad.getNumeroHabitantes());
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public ArrayList<Ciudad> getCiudaddes(ArrayList<CiudadService> ciudadesService) {
        ArrayList<Ciudad> ciudades = new ArrayList<>();
        for (int i = 0; i < ciudadesService.size(); i++) {
            ciudades.add(ciudadesService.get(i).getCiudad());
        }
        return ciudades;
    }
}