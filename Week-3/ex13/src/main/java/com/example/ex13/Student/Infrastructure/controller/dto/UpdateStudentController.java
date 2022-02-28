package com.example.ex13.Student.Infrastructure.controller.dto;

import com.example.ex13.Student.Application.StudentService;
import com.example.ex13.Student.Infrastructure.controller.dto.input.StudentInputDto;
import com.example.ex13.Student.Infrastructure.controller.dto.output.StudentOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("estudiante")
@RestController
public class UpdateStudentController {

    @Autowired
    StudentService studentService;

    @PutMapping("{id}")
    public StudentOutputDto updateStudent(@PathVariable Integer id, @RequestBody StudentInputDto studentInputDto) throws Exception {

        return studentService.updateStudent(id, studentInputDto);

    }
}
