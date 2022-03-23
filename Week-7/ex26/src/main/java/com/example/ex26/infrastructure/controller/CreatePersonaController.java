package com.example.ex26.infrastructure.controller;

import com.example.ex26.Application.PersonaService;
import com.example.ex26.infrastructure.controller.dto.input.PersonaInputDto;
import com.example.ex26.infrastructure.controller.dto.output.PersonaOutputDto;
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