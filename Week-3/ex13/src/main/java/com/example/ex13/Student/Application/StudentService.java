package com.example.ex13.Student.Application;

import com.example.ex13.Student.Infrastructure.controller.dto.input.StudentInputDto;
import com.example.ex13.Student.Infrastructure.controller.dto.output.StudentOutputDto;

import java.util.List;

public interface StudentService {
    List<StudentOutputDto> getAllStudents();
    StudentOutputDto filterStudentById(Integer id, String outputType) throws Exception;
    StudentInputDto addStudent(StudentInputDto studentInputDto) throws Exception;
    StudentOutputDto updateStudent(Integer id, StudentInputDto studentInputDto) throws Exception;
    void deleteStudent(Integer id) throws Exception;
    //TODO: Anadir lista de asignaturas
}
