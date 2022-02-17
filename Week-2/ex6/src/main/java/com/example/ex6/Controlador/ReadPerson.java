package com.example.ex6.Controlador;

import com.example.ex6.Domain.Persona;
import com.example.ex6.Domain.PersonaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("persona")
public class ReadPerson {
    ObjectMapper object = new ObjectMapper();

    @Autowired
    ArrayList<PersonaService> personas;

    @GetMapping("/{id}")
    public Persona userGetId(@PathVariable int id) {
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getPersona().getId() == id) {
                return personas.get(i).getPersona();
            }
        }
        return null;
    }

    @GetMapping("/nombre/{nombre}")
    public ArrayList<Persona> getPersonByName(@PathVariable String nombre) {
        ArrayList<Persona> personasCoincidentes = new ArrayList<>();
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getPersona().getNombre().equalsIgnoreCase(nombre)) {
                personasCoincidentes.add(personas.get(i).getPersona());
            }
        }
        return personasCoincidentes;
    }
}