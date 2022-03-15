package com.example.ex22.infrastructure.controller;

import com.example.ex22.Application.PersonaService;
import com.example.ex22.infrastructure.controller.dto.input.PersonaInputDto;
import com.example.ex22.infrastructure.controller.dto.output.PersonaOutputDto;
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
