package com.example.ex24.Persona.Infrastructure.controller;

import com.example.ex24.Persona.Application.PersonaService;
import com.example.ex24.Persona.Infrastructure.controller.dto.output.PersonaOutputDto;
import com.example.ex24.Profesor.Infrastructure.controller.dto.output.ProfesorOutputDto;
import com.example.ex24.utils.Feing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin(origins="*",methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
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
