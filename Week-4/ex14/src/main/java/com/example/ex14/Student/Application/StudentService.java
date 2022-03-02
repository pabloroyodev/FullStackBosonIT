package com.example.ex14.Student.Application;

import com.example.ex14.Student.Infrastructure.controller.dto.input.StudentInputDto;
import com.example.ex14.Student.Infrastructure.controller.dto.output.StudentOutputDto;
import com.example.ex14.Student.Infrastructure.controller.dto.output.StudentSimpleOutputDto;

import java.util.List;

public interface StudentService {
    List<StudentOutputDto> getAllStudents();
    StudentSimpleOutputDto filterStudentById(Integer id, String outputType) throws Exception;
    StudentOutputDto addStudent(StudentInputDto studentInputDto) throws Exception;
    StudentOutputDto updateStudent(Integer id, StudentInputDto studentInputDto) throws Exception;
    void deleteStudent(Integer id) throws Exception;
    //TODO: Anadir lista de asignaturas
}
