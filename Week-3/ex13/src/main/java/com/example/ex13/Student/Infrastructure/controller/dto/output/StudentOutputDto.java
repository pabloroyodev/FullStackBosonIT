package com.example.ex13.Student.Infrastructure.controller.dto.output;

import com.example.ex13.Persona.Domain.Persona;
import com.example.ex13.Student.Domain.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class StudentOutputDto implements Serializable {
    @NotNull
    private String idStudent;

    @NotNull
    private Persona persona;

    @NotNull
    private Integer numHoursWeek;

    private String comments;

    @NotNull
    private String branch;

    //TODO: Aqui anadiremos la lista de asignaturas posteriormente



    public StudentOutputDto(Student student){
        if (persona == null) {
            System.out.println("La clase student esta incompleta");
            return;
        }
        setIdStudent(student.getIdStudent());
        setPersona(student.getPersona());
        setNumHoursWeek(student.getNumHoursWeek());
        setComments(student.getComments());
        setBranch(student.getBranch());

        //TODO: Aqui anadiremos la lista de asignaturas posteriormente
    }
}
