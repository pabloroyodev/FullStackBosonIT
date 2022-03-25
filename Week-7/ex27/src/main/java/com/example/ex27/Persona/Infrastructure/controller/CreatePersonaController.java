package com.example.ex27.Persona.Infrastructure.controller;

import com.example.ex27.Persona.Application.PersonaService;
import com.example.ex27.Persona.Infrastructure.controller.dto.input.PersonaInputDto;
import com.example.ex27.Persona.Infrastructure.controller.dto.output.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("persona")
@RestController
public class CreatePersonaController {

    @Autowired
    PersonaService personaService;

    @PostMapping
    public PersonaOutputDto addPersona(@RequestBody PersonaInputDto personaInputDto) throws Exception {
        return personaService.addPersona(personaInputDto);
    }
}