package com.example.ex13.StudentAsignatura.Application;

import com.example.ex13.Student.Infrastructure.controller.dto.input.StudentInputDto;
import com.example.ex13.Student.Infrastructure.controller.dto.output.StudentOutputDto;

import java.util.List;

public interface StudentAsignaturaService {
    List<StudentOutputDto> getAllAsignaturas();
    StudentOutputDto filterAsignaturaById(Integer id) throws Exception;
    StudentOutputDto addAsignatura(StudentInputDto studentInputDto) throws Exception;
    StudentOutputDto updateAsignatura(Integer id, StudentInputDto studentInputDto) throws Exception;
    void deleteAsignatura(Integer id) throws Exception;
}
