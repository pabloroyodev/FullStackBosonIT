package com.example.ex13.StudentAsignatura.Application;

import com.example.ex13.StudentAsignatura.Infrastructure.controller.dto.input.StudentAsignaturaInputDto;
import com.example.ex13.StudentAsignatura.Infrastructure.controller.dto.output.StudentAsignaturaOutputDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentAsignaturaServiceImpl implements StudentAsignaturaService{
    @Override
    public List<StudentAsignaturaOutputDto> getAllAsignaturas() {
        return null;
    }

    @Override
    public StudentAsignaturaOutputDto filterAsignaturaById(Integer id) throws Exception {
        return null;
    }

    @Override
    public StudentAsignaturaOutputDto addAsignatura(StudentAsignaturaInputDto studentAsignaturaInputDto) throws Exception {
        return null;
    }

    @Override
    public StudentAsignaturaOutputDto updateAsignatura(Integer id, StudentAsignaturaInputDto studentAsignaturaInputDto) throws Exception {
        return null;
    }

    @Override
    public void deleteAsignatura(Integer id) throws Exception {

    }
}
