package com.example.ex13.Student.Application;

import java.util.List;

public interface StudentService {
    List<StudentOutputDto> getAllStudents();
    StudentOutputDto filterStudentById(String id, String outputType) throws Exception;
    StudentInputDto addStudent(StudentInputDto studentInputDto) throws Exception;
    StudentOutputDto updateStudent(String id, StudentInputDto studentInputDto) throws Exception;
    //StudentOutputDto updatePatchStudent(Integer id, StudentInputDto studentInputDto) throws Exception;
    void deleteStudent(String id) throws Exception;
    List<String> assignAsignaturas(String id, List<String> asignaturas);
    List<String> unassignAsignaturas(String id, List<String> asignaturas);
}
