package com.example.ex5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {

    @Autowired
    @Qualifier("PersonaServiceImpl")
    PersonaService personaService;

    @Autowired
    CiudadService ciudadService;

    @Autowired
    ArrayList<CiudadService> ciudades;

    @GetMapping("/addPersona")
    public Persona addUser(@RequestParam(value = "nombre") String nombre, @RequestParam(value = "ciudad") String ciudad
            , @RequestParam(value = "edad") String edad) {
        return personaService.crearPersona(nombre, ciudad, Integer.parseInt(edad));
    }

    @PostMapping("/addCiudad")
    public void addCity(@RequestHeader(value = "nombre") String nombre
            , @RequestHeader(value = "numeroHabitantes") String numeroHabitantes) {
        ciudadService.crearCiudad(nombre, Integer.parseInt(numeroHabitantes));
        ciudades.add(ciudadService);
        System.out.println(ciudadService.getCiudaddes(ciudades));
    }
}