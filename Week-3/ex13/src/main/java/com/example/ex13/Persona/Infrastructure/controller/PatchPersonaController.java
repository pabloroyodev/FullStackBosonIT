package com.example.ex13.Persona.Infrastructure.controller;

import com.example.ex13.Persona.Application.PersonaService;
import com.example.ex13.Persona.Infrastructure.controller.dto.input.PersonaInputDto;
import com.example.ex13.Persona.Infrastructure.controller.dto.output.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("persona")
@RestController
public class PatchPersonaController {

    @Autowired
    PersonaService personaService;

    @PatchMapping("{id}")
    public PersonaOutputDto updatePatchPersona(@PathVariable Integer id, @RequestBody PersonaInputDto personaInputDto) throws Exception {

        return personaService.updatePatchPersona(id, personaInputDto);

    }
}
