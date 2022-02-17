package com.example.ex6.Controlador;

import com.example.ex6.Domain.Persona;
import com.example.ex6.Domain.PersonaService;
import com.example.ex6.Domain.PersonaServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@RequestMapping("persona")
public class CreatePerson {

    ObjectMapper object = new ObjectMapper();
    private final AtomicInteger counter = new AtomicInteger();

    @Autowired
    ArrayList<PersonaService> personas;

    @PostMapping()
    public void addPerson(@RequestBody String jsonParam) throws JsonProcessingException {
        PersonaService persona = new PersonaServiceImpl();
        persona.crearPersona(counter.incrementAndGet(), object.readValue(jsonParam, Persona.class));
        personas.add(persona);
        System.out.println(persona.getPersona());
    }
}