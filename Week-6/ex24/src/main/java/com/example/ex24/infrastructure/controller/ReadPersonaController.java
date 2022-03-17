package com.example.ex24.infrastructure.controller;

import com.example.ex24.Application.PersonaService;
import com.example.ex24.domain.Persona;
import com.example.ex24.infrastructure.controller.dto.output.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("persona")
@RestController
public class ReadPersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping
    public List<PersonaOutputDto> findAll(){
        return personaService.getAllPersonas();
    }

    @GetMapping("{id}")
    public PersonaOutputDto getPersonaById(@PathVariable String id) throws Exception {
        return personaService.filterPersonaById(id);
    }

    @GetMapping("/{usuario}/usuario")
    public List<Persona> getPersonaByUsuario(@PathVariable String usuario){
        return personaService.filterPersonaByUser(usuario);
    }
}
