package com.example.ex11.infrastructure.controller.dto.CRUD;

import com.example.ex11.Application.PersonaService;
import com.example.ex11.infrastructure.controller.dto.input.PersonaInputDto;
import com.example.ex11.infrastructure.controller.dto.output.PersonaOutputDto;
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
