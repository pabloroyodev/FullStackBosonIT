package com.example.ex14.Profesor.Infrastructure.controller;

import com.example.ex14.Profesor.Application.ProfesorService;
import com.example.ex14.Profesor.Infrastructure.controller.dto.output.ProfesorOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("profesor")
@RestController
public class ReadProfesor {

    @Autowired
    ProfesorService profesorService;

    @GetMapping
    public List<ProfesorOutputDto> findAll(){
        return profesorService.getAllProfesores();
    }

    @GetMapping("{id}")
    public ProfesorOutputDto getProfesorById(@PathVariable Integer id) throws Exception {
        return profesorService.filterProfesorById(id);
    }
}
