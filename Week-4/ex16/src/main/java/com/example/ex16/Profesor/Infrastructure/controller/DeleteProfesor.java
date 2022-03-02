package com.example.ex16.Profesor.Infrastructure.controller;

import com.example.ex16.Profesor.Application.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("profesor")
@RestController
public class DeleteProfesor {

    @Autowired
    ProfesorService profesorService;

    @DeleteMapping("{id}")
    public void deleteProfesor(@PathVariable Integer id) throws Exception {
        profesorService.deleteProfesor(id);
    }
}
