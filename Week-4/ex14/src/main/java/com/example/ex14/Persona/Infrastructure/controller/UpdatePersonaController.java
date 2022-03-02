package com.example.ex14.Persona.Infrastructure.controller;

import com.example.ex14.Persona.Application.PersonaService;
import com.example.ex14.Persona.Infrastructure.controller.dto.input.PersonaInputDto;
import com.example.ex14.Persona.Infrastructure.controller.dto.output.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("persona")
@RestController
public class UpdatePersonaController {

    @Autowired
    PersonaService personaService;

    @PutMapping("{id}")
    public PersonaOutputDto updatePersona(@PathVariable Integer id, @RequestBody PersonaInputDto personaInputDto) throws Exception {

        return personaService.updatePersona(id, personaInputDto);

    }
}
