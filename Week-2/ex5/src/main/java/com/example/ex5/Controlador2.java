package com.example.ex5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {
    @Autowired
    @Qualifier("PersonaServiceImpl")
    PersonaService personaService;

    @Autowired
    ArrayList<CiudadService> ciudades;

    @Autowired
    CiudadService ciudadService;

    @GetMapping("/getPersona")
    public Persona getPersona() {
        return new Persona(personaService.getNombre(),personaService.getCiudad(), (personaService.getEdad())*2);
    }

    @GetMapping("/getCiudad")
    public  ArrayList<Ciudad> getCiudades() {
        return ciudadService.getCiudaddes(ciudades);
    }

}
