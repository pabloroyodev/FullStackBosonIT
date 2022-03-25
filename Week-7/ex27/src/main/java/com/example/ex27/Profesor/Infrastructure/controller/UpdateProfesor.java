package com.example.ex27.Profesor.Infrastructure.controller;

import com.example.ex27.Profesor.Application.ProfesorService;
import com.example.ex27.Profesor.Infrastructure.controller.dto.input.ProfesorInputDto;
import com.example.ex27.Profesor.Infrastructure.controller.dto.output.ProfesorOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("profesor")
@RestController
public class UpdateProfesor {

    @Autowired
    ProfesorService profesorService;

    @PutMapping("{id}")
    public ProfesorOutputDto updateStudent(@PathVariable Integer id, @RequestBody ProfesorInputDto profesorInputDto) throws Exception {

        return profesorService.updateProfesor(id, profesorInputDto);

    }
}
