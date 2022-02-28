package com.example.ex13.Profesor.Infrastructure.controller;

import com.example.ex13.Profesor.Application.ProfesorService;
import com.example.ex13.Profesor.Infrastructure.controller.dto.output.ProfesorOutputDto;
import com.example.ex13.Student.Infrastructure.controller.dto.output.StudentOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
