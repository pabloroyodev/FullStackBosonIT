package com.example.ex24.Persona.Infrastructure.controller;

import com.example.ex24.Persona.Application.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*",methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
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
