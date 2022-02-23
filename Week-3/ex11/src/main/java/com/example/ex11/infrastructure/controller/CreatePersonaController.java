package com.example.ex11.infrastructure.controller;

import com.example.ex11.Application.PersonaService;
import com.example.ex11.infrastructure.controller.dto.input.PersonaInputDto;
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
    public PersonaInputDto addPersona(@RequestBody PersonaInputDto personaInputDto) throws Exception {
        return personaService.addPersona(personaInputDto);
    }
}