package com.example.ex15.Profesor.Infrastructure.controller;

import com.example.ex15.Profesor.Application.ProfesorService;
import com.example.ex15.Profesor.Infrastructure.controller.dto.input.ProfesorInputDto;
import com.example.ex15.Profesor.Infrastructure.controller.dto.output.ProfesorOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("profesor")
@RestController
public class CreateProfesor {
    @Autowired
    ProfesorService profesorService;

    @PostMapping
    public ProfesorOutputDto addProfesor(@RequestBody ProfesorInputDto profesorInputDto) throws Exception {
        return profesorService.addProfesor(profesorInputDto);
    }
}
