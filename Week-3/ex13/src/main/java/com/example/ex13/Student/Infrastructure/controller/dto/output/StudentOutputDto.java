package com.example.ex13.Student.Infrastructure.controller.dto.output;

import com.example.ex13.Persona.Domain.Persona;
import com.example.ex13.Persona.Infrastructure.controller.dto.output.PersonaOutputDto;
import com.example.ex13.Student.Domain.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class StudentOutputDto implements Serializable {
    @NotNull
    private Integer idStudent;

    @NotNull
    private PersonaOutputDto personaOutputDto;

    @NotNull
    private Integer numHoursWeek;

    private String comments;

    @NotNull
    private String branch;

    //TODO: Aqui anadiremos la lista de asignaturas posteriormente



    public StudentOutputDto(Student student){
        if (student == null) {
            System.out.println("La clase student esta incompleta");
            return;
        }
        setIdStudent(student.getIdStudent());
        setPersonaOutputDto(new PersonaOutputDto(student.getPersona()));
        setNumHoursWeek(student.getNumHoursWeek());
        setComments(student.getComments());
        setBranch(student.getBranch());

        //TODO: Aqui anadiremos la lista de asignaturas posteriormente
    }
}
