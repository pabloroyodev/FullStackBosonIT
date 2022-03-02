package com.example.ex15.Student.Infrastructure.controller;

import com.example.ex15.Student.Application.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("estudiante")
@RestController
public class DeleteStudentController {

    @Autowired
    StudentService studentService;

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable Integer id) throws Exception {
        studentService.deleteStudent(id);
    }
}
