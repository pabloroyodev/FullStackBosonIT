package com.example.ex13.Student.Infrastructure.controller;

import com.example.ex13.Student.Application.StudentService;
import com.example.ex13.Student.Infrastructure.controller.dto.input.StudentInputDto;
import com.example.ex13.Student.Infrastructure.controller.dto.output.StudentOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("estudiante")
@RestController
public class CreateStudentController {
    @Autowired
    StudentService studentService;

    @PostMapping
    public StudentOutputDto addStudent(@RequestBody StudentInputDto studentInputDto) throws Exception {
        return studentService.addStudent(studentInputDto);
    }
}
