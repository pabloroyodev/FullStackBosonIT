package com.example.ex14.StudentAsignatura.Infrastructure.controller;
;
import com.example.ex14.StudentAsignatura.Application.StudentAsignaturaService;
import com.example.ex14.StudentAsignatura.Infrastructure.controller.dto.input.StudentAsignaturaInputDto;
import com.example.ex14.StudentAsignatura.Infrastructure.controller.dto.output.StudentAsignaturaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("asignatura")
@RestController
public class UpdateStudentAsignaturaController {
    @Autowired
    StudentAsignaturaService studentAsignaturaService;

    @PutMapping("{id}")
    public StudentAsignaturaOutputDto updateAsignatura(@PathVariable Integer id, @RequestBody StudentAsignaturaInputDto studentAsignaturaInputDto) throws Exception {

        return studentAsignaturaService.updateAsignatura(id, studentAsignaturaInputDto);

    }
}
