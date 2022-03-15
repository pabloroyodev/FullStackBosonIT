package com.example.ex23.infrastructure.controller;

import com.example.ex23.Application.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("persona")
@RestController
public class DeletePersonaController {

    @Autowired
    PersonaService personaService;

    @DeleteMapping("{id}")
    public void deletePersona(@PathVariable Integer id) throws Exception {
        personaService.deletePersona(id);
    }
}
