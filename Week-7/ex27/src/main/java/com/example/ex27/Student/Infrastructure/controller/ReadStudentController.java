package com.example.ex27.Student.Infrastructure.controller;

import com.example.ex27.Student.Application.StudentService;
import com.example.ex27.Student.Infrastructure.controller.dto.output.StudentOutputDto;
import com.example.ex27.Student.Infrastructure.controller.dto.output.StudentSimpleOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("estudiante")
@RestController
public class ReadStudentController {

    @Autowired
    StudentService studentService;

    @GetMapping
    public List<StudentOutputDto> findAll(){
        return studentService.getAllStudents();
    }

    @GetMapping("{id}")
    public StudentSimpleOutputDto getStudentById(@PathVariable Integer id, @Value("simple") @RequestParam(name = "outputType", defaultValue = "simple", required = false)
            String outputType) throws Exception {
        return studentService.filterStudentById(id, outputType);
    }
}
