package com.example.ex13.Profesor.Application;

import com.example.ex13.Persona.Infrastructure.repository.jpa.PersonaRepositorio;
import com.example.ex13.Profesor.Domain.Profesor;
import com.example.ex13.Profesor.Infrastructure.controller.dto.input.ProfesorInputDto;
import com.example.ex13.Profesor.Infrastructure.controller.dto.output.ProfesorOutputDto;
import com.example.ex13.Profesor.Infrastructure.repository.jpa.ProfesorRepositorio;
import com.example.ex13.Student.Domain.Student;
import com.example.ex13.Student.Infrastructure.controller.dto.input.StudentInputDto;
import com.example.ex13.Student.Infrastructure.controller.dto.output.StudentOutputDto;
import com.example.ex13.Student.Infrastructure.repository.jpa.StudentRepositorio;
import com.example.ex13.exceptions.NotFoundException;
import com.example.ex13.exceptions.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfesorServiceImpl implements ProfesorService{
    @Autowired
    StudentRepositorio studentRepositorio;

    @Autowired
    PersonaRepositorio personaRepositorio;

    @Autowired
    ProfesorRepositorio profesorRepositorio;

    @Override
    public List<ProfesorOutputDto> getAllProfesores() {
        List<Profesor> profesors = profesorRepositorio.findAll();

        List<ProfesorOutputDto> profesorOutputDto = profesors.stream().map(p -> new ProfesorOutputDto(p)).collect(Collectors.toList());
        return profesorOutputDto;
    }

    @Override
    public ProfesorOutputDto filterProfesorById(Integer id) throws Exception {
        Profesor profesor =
                profesorRepositorio.findById(id).orElseThrow(() -> new NotFoundException(id + " not found."));

        ProfesorOutputDto profesorOutputDto  = new ProfesorOutputDto(profesor);
        return profesorOutputDto;
    }

    @Override
    public ProfesorOutputDto addProfesor(ProfesorInputDto profesorInputDto) throws Exception {
        List<Student> students = studentRepositorio.findAll();
        for (int i = 0; i < students.size(); i++){
            if(profesorInputDto.getIdPersona().equals(students.get(i).getPersona().getIdPersona())){
                throw new UnprocesableException("La persona "+profesorInputDto.getIdPersona()+ " es un estudiante!");
            }
        }
        Profesor profesor = profesorInputDtoToEntity(profesorInputDto);
        profesorRepositorio.save(profesor);
        ProfesorOutputDto profesorOutputDto = new ProfesorOutputDto(profesor);
        return profesorOutputDto;
    }

    @Override
    public ProfesorOutputDto updateProfesor(Integer id, ProfesorInputDto profesorInputDto) throws Exception {
        Profesor profesor =
                profesorRepositorio
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException("Profesor id: " + id + " not found"));

        profesor.setPersona(personaRepositorio.findById(profesorInputDto.getIdPersona()).orElseThrow(()->new NotFoundException("Persona not found")));
        profesor.setComments(profesorInputDto.getComments());
        profesor.setBranch(profesorInputDto.getBranch());

        profesorRepositorio.save(profesor);
        ProfesorOutputDto profesorOutputDto = new ProfesorOutputDto(profesor);
        return profesorOutputDto;
    }

    @Override
    public void deleteProfesor(Integer id) throws Exception {
        profesorRepositorio.delete(
                profesorRepositorio
                        .findById(id)
                        .orElseThrow(() -> new UnprocesableException(id + " not found")));
    }

    private Profesor profesorInputDtoToEntity(ProfesorInputDto profesorInputDto){
        Profesor profesor = new Profesor();

        profesor.setPersona(personaRepositorio.findById(Integer.valueOf(profesorInputDto.getIdPersona())).orElseThrow(()->new NotFoundException("Persona not found")));
        profesor.setComments(profesorInputDto.getComments());
        profesor.setBranch(profesorInputDto.getBranch());

        return profesor;
    }
}
