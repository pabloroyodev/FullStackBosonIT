package com.example.ex27.StudentAsignatura.Infrastructure.controller;

import com.example.ex27.StudentAsignatura.Application.StudentAsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("asignatura")
@RestController
public class DeleteStudentAsignaturaController {
    @Autowired
    StudentAsignaturaService studentAsignaturaService;

    @DeleteMapping("{id}")
    public void deleteAsignatura(@PathVariable Integer id) throws Exception {
        studentAsignaturaService.deleteAsignatura(id);
    }
}
