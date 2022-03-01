package com.example.ex13.StudentAsignatura.Application;

import com.example.ex13.Persona.Infrastructure.repository.jpa.PersonaRepositorio;
import com.example.ex13.Profesor.Infrastructure.repository.jpa.ProfesorRepositorio;
import com.example.ex13.Student.Domain.Student;
import com.example.ex13.Student.Infrastructure.repository.jpa.StudentRepositorio;
import com.example.ex13.StudentAsignatura.Domain.StudentAsignatura;
import com.example.ex13.StudentAsignatura.Infrastructure.controller.dto.input.StudentAsignaturaInputDto;
import com.example.ex13.StudentAsignatura.Infrastructure.controller.dto.output.StudentAsignaturaOutputDto;
import com.example.ex13.StudentAsignatura.Infrastructure.repository.jpa.StudentAsignaturaRepositorio;
import com.example.ex13.exceptions.NotFoundException;
import com.example.ex13.exceptions.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentAsignaturaServiceImpl implements StudentAsignaturaService{
    @Autowired
    ProfesorRepositorio profesorRepositorio;

    @Autowired
    StudentRepositorio studentRepositorio;

    @Autowired
    StudentAsignaturaRepositorio studentAsignaturaRepositorio;

    @Override
    public List<StudentAsignaturaOutputDto> getAllAsignaturas() {
        List<StudentAsignatura> studentAsignaturas = studentAsignaturaRepositorio.findAll();

        List<StudentAsignaturaOutputDto> studentAsignaturaOutputDto = studentAsignaturas.stream().map(s -> new StudentAsignaturaOutputDto(s)).collect(Collectors.toList());
        return studentAsignaturaOutputDto;
    }

    @Override
    public StudentAsignaturaOutputDto filterAsignaturaById(Integer id) throws Exception {
        StudentAsignatura studentAsignatura =
                studentAsignaturaRepositorio.findById(id).orElseThrow(() -> new NotFoundException(id + " not found."));

        StudentAsignaturaOutputDto studentAsignaturaOutputDto = new StudentAsignaturaOutputDto(studentAsignatura);
        return studentAsignaturaOutputDto;
    }

    @Override
    public StudentAsignaturaOutputDto addAsignatura(StudentAsignaturaInputDto studentAsignaturaInputDto) throws Exception {
        StudentAsignatura studentAsignatura = studentAsignaturaInputDtoToEntity(studentAsignaturaInputDto);
        studentAsignaturaRepositorio.save(studentAsignatura);
        StudentAsignaturaOutputDto studentAsignaturaOutputDto = new StudentAsignaturaOutputDto(studentAsignatura);
        return studentAsignaturaOutputDto;
    }

    @Override
    public StudentAsignaturaOutputDto updateAsignatura(Integer id, StudentAsignaturaInputDto studentAsignaturaInputDto) throws Exception {
        StudentAsignatura studentAsignatura =
                studentAsignaturaRepositorio
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException("Asignatura id: " + id + " not found"));


        studentAsignatura.setProfesor(profesorRepositorio.findById(studentAsignaturaInputDto.getIdProfesor()).orElseThrow(()->new NotFoundException("Profesor not found")));
        studentAsignatura.setAsignatura(studentAsignaturaInputDto.getAsignatura());
        studentAsignatura.setComments(studentAsignaturaInputDto.getComments());
        studentAsignatura.setInitialDate(studentAsignaturaInputDto.getInitialDate());
        studentAsignatura.setFinishDate(studentAsignaturaInputDto.getFinishDate());

        studentAsignaturaRepositorio.save(studentAsignatura);
        StudentAsignaturaOutputDto studentAsignaturaOutputDto = new StudentAsignaturaOutputDto(studentAsignatura);
        return studentAsignaturaOutputDto;
    }

    @Override
    public void deleteAsignatura(Integer id) throws Exception {
        studentAsignaturaRepositorio.delete(
                studentAsignaturaRepositorio
                        .findById(id)
                        .orElseThrow(() -> new UnprocesableException(id + " not found")));
    }

    private StudentAsignatura studentAsignaturaInputDtoToEntity(StudentAsignaturaInputDto studentAsignaturaInputDto){
        StudentAsignatura studentAsignatura = new StudentAsignatura();

        studentAsignatura.setProfesor(profesorRepositorio.findById(studentAsignaturaInputDto.getIdProfesor()).orElseThrow(()->new NotFoundException("Profesor not found")));
        studentAsignatura.setAsignatura(studentAsignaturaInputDto.getAsignatura());
        studentAsignatura.setComments(studentAsignaturaInputDto.getComments());
        studentAsignatura.setInitialDate(studentAsignaturaInputDto.getInitialDate());
        studentAsignatura.setFinishDate(studentAsignaturaInputDto.getFinishDate());

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < studentAsignaturaInputDto.getStudents().size(); i++) {
            students.add(studentRepositorio.findById(studentAsignaturaInputDto.getStudents().get(i)).orElseThrow(()-> new NotFoundException("Estudiante not found")));
        }
        studentAsignatura.setStudents(students);

        return studentAsignatura;
    }
}
