package com.example.ex24.StudentAsignatura.Infrastructure.controller;

import com.example.ex24.StudentAsignatura.Application.StudentAsignaturaService;
import com.example.ex24.StudentAsignatura.Infrastructure.controller.dto.output.StudentAsignaturaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("asignatura")
@RestController
public class ReadStudentAsignaturaController {
    @Autowired
    StudentAsignaturaService studentAsignaturaService;

    @GetMapping
    public List<StudentAsignaturaOutputDto> findAll(){
        return studentAsignaturaService.getAllAsignaturas();
    }

    @GetMapping("{id}")
    public StudentAsignaturaOutputDto getAsignaturaById(@PathVariable Integer id) throws Exception {
        return studentAsignaturaService.filterAsignaturaById(id);
    }
}
