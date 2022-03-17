package com.example.ex24.infrastructure.controller;

import com.example.ex24.Application.PersonaServiceImpl;
import com.example.ex24.infrastructure.controller.dto.input.PersonaInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("persona")
@RestController
public class CreatePersonaController {

    @Autowired
    PersonaServiceImpl personaServiceimpl;

    @PostMapping
    public PersonaInputDto addPersona(@RequestBody PersonaInputDto personaInputDto) throws Exception {
        return personaServiceimpl.addPersona(personaInputDto);
    }
}