package com.example.ex13.Student.Application;

import com.example.ex13.Persona.Infrastructure.repository.jpa.PersonaRepositorio;
import com.example.ex13.Student.Domain.Student;
import com.example.ex13.Student.Infrastructure.controller.dto.input.StudentInputDto;
import com.example.ex13.Student.Infrastructure.controller.dto.output.StudentFullOutputDto;
import com.example.ex13.Student.Infrastructure.controller.dto.output.StudentOutputDto;
import com.example.ex13.Student.Infrastructure.repository.jpa.StudentRepositorio;
import com.example.ex13.exceptions.NotFoundException;
import com.example.ex13.exceptions.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    PersonaRepositorio personaRepositorio;

    @Autowired
    StudentRepositorio studentRepositorio;

    //TODO: Implementar en un futuro el repositorio de asignaturas

    @Override
    public List<StudentOutputDto> getAllStudents() {
        List<Student> students = studentRepositorio.findAll();

        List<StudentOutputDto> studentOutputDto = students.stream().map(s -> new StudentOutputDto(s)).collect(Collectors.toList());
        return studentOutputDto;
    }

    @Override
    public StudentOutputDto filterStudentById(String id, String outputType) throws Exception {
        Student student =
                studentRepositorio.findById(id).orElseThrow(() -> new NotFoundException(id + " not found."));

        if(outputType.equalsIgnoreCase("full")){
            StudentFullOutputDto studentFullOutputDto  = new StudentFullOutputDto(student);
            return studentFullOutputDto;
        }else{
            StudentOutputDto studentOutputDto = new StudentOutputDto(student);
            return studentOutputDto;
        }
    }

    @Override
    public StudentInputDto addStudent(StudentInputDto studentInputDto) throws Exception {
        Student student = studentInputDtoToEntity(studentInputDto);
        studentRepositorio.saveAndFlush(student);

        return studentInputDto;
    }

    @Override
    public StudentOutputDto updateStudent(String id, StudentInputDto studentInputDto) throws Exception {
        Student student =
                studentRepositorio
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException("Student id: " + id + " not found"));

        student.setPersona(personaRepositorio.findById(studentInputDto.getIdPersona()).orElseThrow(()->new NotFoundException("Persona not found")));
        student.setNumHoursWeek(studentInputDto.getNumHoursWeek());
        student.setComments(studentInputDto.getComments());
        student.setBranch(studentInputDto.getBranch());

        studentRepositorio.saveAndFlush(student);
        StudentOutputDto studentOutputDto = new StudentOutputDto(student);
        return studentOutputDto;
    }

    @Override
    public void deleteStudent(String id) throws Exception {
        studentRepositorio.delete(
                studentRepositorio
                        .findById(id)
                        .orElseThrow(() -> new UnprocesableException(id + " not found")));
    }

    private Student studentInputDtoToEntity(StudentInputDto studentInputDto){
        Student student = new Student();

        student.setPersona(personaRepositorio.findById(Integer.valueOf(studentInputDto.getIdPersona())).orElseThrow(()->new NotFoundException("Persona not found")));
        student.setNumHoursWeek(studentInputDto.getNumHoursWeek());
        student.setComments(studentInputDto.getComments());
        student.setBranch(studentInputDto.getBranch());

        //TODO: Anadir lista de asignaturas
        return student;
    }

}
