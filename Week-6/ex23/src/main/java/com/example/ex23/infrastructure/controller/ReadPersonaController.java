package com.example.ex23.infrastructure.controller;

import com.example.ex23.Application.PersonaService;
import com.example.ex23.infrastructure.controller.dto.output.PersonaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RequestMapping("persona")
@RestController
public class ReadPersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping
    public List<PersonaOutputDto> findAll(){
        return personaService.getAllPersonas();
    }

    @GetMapping("{id}")
    public PersonaOutputDto getPersonaById(@PathVariable Integer id) throws Exception {
        return personaService.filterPersonaById(id);
    }

    @GetMapping("/{usuario}/usuario")
    public List<PersonaOutputDto> getPersonaByUsuario(@PathVariable String usuario){
        return personaService.filterPersonaByUser(usuario);
    }

    @GetMapping("/criteria")
    public List<PersonaOutputDto> filterByCriteria(@RequestParam(required=false, name="user") String user,
                                          @RequestParam(required=false, name="name") String name,
                                          @RequestParam(required=false, name="surname") String surname,
                                          @RequestParam(required=false, name="createdDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate createdDate,
                                          @RequestParam(required=false, name="dateCondition") String dateCondition,
                                          @RequestParam(required=false, name="order") String order,
                                          @RequestParam(required=false, name="page") Integer page) {
        return personaService.filterPersonaByCriteria(user, name, surname, createdDate, dateCondition, order, page);
    }
}
