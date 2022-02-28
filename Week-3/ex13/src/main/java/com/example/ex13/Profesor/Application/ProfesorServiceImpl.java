package com.example.ex13.Profesor.Application;

import com.example.ex13.Profesor.Infrastructure.controller.dto.input.ProfesorInputDto;
import com.example.ex13.Profesor.Infrastructure.controller.dto.output.ProfesorOutputDto;

import java.util.List;

public class ProfesorServiceImpl implements ProfesorService{
    @Override
    public List<ProfesorOutputDto> getAllProfesores() {
        return null;
    }

    @Override
    public ProfesorOutputDto filterProfesorById(Integer id) throws Exception {
        return null;
    }

    @Override
    public ProfesorInputDto addProfesor(ProfesorInputDto profesorInputDto) throws Exception {
        return null;
    }

    @Override
    public ProfesorOutputDto updateProfesor(Integer id, ProfesorInputDto profesorInputDto) throws Exception {
        return null;
    }

    @Override
    public void deleteProfesor(Integer id) throws Exception {

    }
}
