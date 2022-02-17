package com.example.ex6.Controlador;

import com.example.ex6.Domain.PersonaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("persona")
public class DeletePerson {
    ObjectMapper object = new ObjectMapper();

    @Autowired
    ArrayList<PersonaService> personas;

    @DeleteMapping("/{id}")
    public void removePersonById(@PathVariable int id) {
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getPersona().getId() == id) {
                personas.remove(i);
                System.out.println("Se ha eliminado la persona con id " + id);
            }
        }
    }
}