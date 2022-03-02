package com.example.ex16.Student.Application;

import com.example.ex16.Persona.Infrastructure.repository.jpa.PersonaRepositorio;
import com.example.ex16.Profesor.Domain.Profesor;
import com.example.ex16.Profesor.Infrastructure.repository.jpa.ProfesorRepositorio;
import com.example.ex16.Student.Domain.Student;
import com.example.ex16.Student.Infrastructure.controller.dto.input.StudentInputDto;
import com.example.ex16.Student.Infrastructure.controller.dto.output.StudentFullOutputDto;
import com.example.ex16.Student.Infrastructure.controller.dto.output.StudentOutputDto;
import com.example.ex16.Student.Infrastructure.controller.dto.output.StudentSimpleOutputDto;
import com.example.ex16.Student.Infrastructure.repository.jpa.StudentRepositorio;
import com.example.ex16.StudentAsignatura.Domain.StudentAsignatura;
import com.example.ex16.StudentAsignatura.Infrastructure.repository.jpa.StudentAsignaturaRepositorio;
import com.example.ex16.exceptions.NotFoundException;
import com.example.ex16.exceptions.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    ProfesorRepositorio profesorRepositorio;

    @Autowired
    PersonaRepositorio personaRepositorio;

    @Autowired
    StudentRepositorio studentRepositorio;

    @Autowired
    StudentAsignaturaRepositorio studentAsignaturaRepositorio;

    //TODO: Implementar en un futuro el repositorio de asignaturas

    @Override
    public List<StudentOutputDto> getAllStudents() {
        List<Student> students = studentRepositorio.findAll();

        List<StudentOutputDto> studentOutputDto = students.stream().map(s -> new StudentOutputDto(s)).collect(Collectors.toList());
        return studentOutputDto;
    }

    @Override
    public StudentSimpleOutputDto filterStudentById(Integer id, String outputType) throws Exception {
        Student student =
                studentRepositorio.findById(id).orElseThrow(() -> new NotFoundException(id + " not found."));

        if (outputType.equalsIgnoreCase("full")) {
            StudentFullOutputDto studentFullOutputDto  = new StudentFullOutputDto(student);
            return studentFullOutputDto;
        } else {
            StudentSimpleOutputDto studentSimpleOutputDto = new StudentSimpleOutputDto(student);
            return studentSimpleOutputDto;
        }
    }

    @Override
    public StudentOutputDto addStudent(StudentInputDto studentInputDto) throws Exception {
        List<Profesor> profesores = profesorRepositorio.findAll();
        for (int i = 0; i < profesores.size(); i++){
            if(studentInputDto.getIdPersona().equals(profesores.get(i).getPersona().getIdPersona())){
                throw new UnprocesableException("La persona "+studentInputDto.getIdPersona()+ " es un profesor!");
            }
        }

        Student student = studentInputDtoToEntity(studentInputDto);
        studentRepositorio.save(student);
        StudentOutputDto studentOutputDto = new StudentOutputDto(student);
        return studentOutputDto;
    }

    @Override
    public StudentOutputDto updateStudent(Integer id, StudentInputDto studentInputDto) throws Exception {
        Student student =
                studentRepositorio
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException("Student id: " + id + " not found"));

        student.setPersona(personaRepositorio.findById(studentInputDto.getIdPersona()).orElseThrow(()->new NotFoundException("Persona not found")));
        student.setNumHoursWeek(studentInputDto.getNumHoursWeek());
        student.setComments(studentInputDto.getComments());
        student.setBranch(studentInputDto.getBranch());
        if (studentInputDto.getAsignaturas()!=null)
        {
            List<StudentAsignatura> studentAsignaturas=new ArrayList<>();
            for (int asignatura: studentInputDto.getAsignaturas())
            {
                StudentAsignatura studentAsignatura= studentAsignaturaRepositorio.findById(asignatura).orElseThrow(()-> new NotFoundException("Asignatura Not Fund"));
                studentAsignaturas.add(studentAsignatura);
            }
            student.setAsignaturas(studentAsignaturas);
        }

        studentRepositorio.save(student);
        StudentOutputDto studentOutputDto = new StudentOutputDto(student);
        return studentOutputDto;
    }

    @Override
    public void deleteStudent(Integer id) throws Exception {
        studentRepositorio.delete(
                studentRepositorio
                        .findById(id)
                        .orElseThrow(() -> new UnprocesableException(id + " not found")));
    }

    private Student studentInputDtoToEntity(StudentInputDto studentInputDto){
        Student student = new Student();

        student.setPersona(personaRepositorio.findById(studentInputDto.getIdPersona()).orElseThrow(()->new NotFoundException("Persona not found")));
        student.setNumHoursWeek(studentInputDto.getNumHoursWeek());
        student.setComments(studentInputDto.getComments());
        student.setBranch(studentInputDto.getBranch());

        if (studentInputDto.getAsignaturas()!=null)
        {
            List<StudentAsignatura> studentAsignaturas=new ArrayList<>();
            for (int asignatura: studentInputDto.getAsignaturas())
            {
                StudentAsignatura studentAsignatura= studentAsignaturaRepositorio.findById(asignatura).orElseThrow(()-> new NotFoundException("Asignatura Not Fund"));
                studentAsignaturas.add(studentAsignatura);
            }
            student.setAsignaturas(studentAsignaturas);
        }

        return student;
    }

}
