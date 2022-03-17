package com.example.ex24.StudentAsignatura.Infrastructure.controller;

import com.example.ex24.StudentAsignatura.Application.StudentAsignaturaService;
import com.example.ex24.StudentAsignatura.Infrastructure.controller.dto.input.StudentAsignaturaInputDto;
import com.example.ex24.StudentAsignatura.Infrastructure.controller.dto.output.StudentAsignaturaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("asignatura")
@RestController
public class CreateStudentAsignaturaController {
    @Autowired
    StudentAsignaturaService studentAsignaturaService;

    @PostMapping
    public StudentAsignaturaOutputDto addStudentAsignatura(@RequestBody StudentAsignaturaInputDto studentAsignaturaInputDto) throws Exception {
        return studentAsignaturaService.addAsignatura(studentAsignaturaInputDto);
    }
}
