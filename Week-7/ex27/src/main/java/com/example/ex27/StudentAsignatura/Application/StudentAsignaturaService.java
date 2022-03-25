package com.example.ex27.StudentAsignatura.Application;

import com.example.ex27.StudentAsignatura.Infrastructure.controller.dto.input.StudentAsignaturaInputDto;
import com.example.ex27.StudentAsignatura.Infrastructure.controller.dto.output.StudentAsignaturaOutputDto;

import java.util.List;

public interface StudentAsignaturaService {
    List<StudentAsignaturaOutputDto> getAllAsignaturas();
    StudentAsignaturaOutputDto filterAsignaturaById(Integer id) throws Exception;
    StudentAsignaturaOutputDto addAsignatura(StudentAsignaturaInputDto studentAsignaturaInputDto) throws Exception;
    StudentAsignaturaOutputDto updateAsignatura(Integer id, StudentAsignaturaInputDto studentAsignaturaInputDto) throws Exception;
    void deleteAsignatura(Integer id) throws Exception;
}
