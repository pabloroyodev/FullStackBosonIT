package com.example.ex13.Profesor.Application;

import com.example.ex13.Profesor.Infrastructure.controller.dto.input.ProfesorInputDto;
import com.example.ex13.Profesor.Infrastructure.controller.dto.output.ProfesorOutputDto;

import java.util.List;

public interface ProfesorService {
    List<ProfesorOutputDto> getAllProfesores();
    ProfesorOutputDto filterProfesorById(Integer id) throws Exception;
    //TODO: Creo que se debe devolver ProfesorOutputDto en el addProfesor
    ProfesorInputDto addProfesor(ProfesorInputDto profesorInputDto) throws Exception;
    ProfesorOutputDto updateProfesor(Integer id, ProfesorInputDto profesorInputDto) throws Exception;
    void deleteProfesor(Integer id) throws Exception;
}
