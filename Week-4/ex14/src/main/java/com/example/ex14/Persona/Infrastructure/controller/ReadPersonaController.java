package com.example.ex14.Persona.Infrastructure.controller;

import com.example.ex14.Persona.Application.PersonaService;
import com.example.ex14.Persona.Infrastructure.controller.dto.output.PersonaOutputDto;
import com.example.ex14.Profesor.Infrastructure.controller.dto.output.ProfesorOutputDto;
import com.example.ex14.utils.Feing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequestMapping("persona")
@RestController
public class ReadPersonaController {

    @Autowired
    PersonaService personaService;

    @Autowired
    Feing feign;

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

    @GetMapping("/profesor/{id}")
    ResponseEntity<ProfesorOutputDto> getProfesorRestTemplate(@PathVariable Integer id){
        ResponseEntity<ProfesorOutputDto> rs = new RestTemplate().getForEntity("http://localhost:8081/profesor/"+id,ProfesorOutputDto.class);
        return ResponseEntity.ok(rs.getBody());

    }
    @GetMapping("/feing/{id}")
    ResponseEntity<ProfesorOutputDto> getProfesorFeign(@PathVariable Integer id){
        ResponseEntity<ProfesorOutputDto> rs = feign.callServer(id);
        return rs;
    }
}
