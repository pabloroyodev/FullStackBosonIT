package com.example.ex15.Student.Infrastructure.controller.dto.output;

import com.example.ex15.Persona.Infrastructure.controller.dto.output.PersonaOutputDto;
import com.example.ex15.Student.Domain.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    List<Integer> asignaturas;


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

        List<Integer> asignaturas = new ArrayList<>();
        if(student.getAsignaturas() != null) {
            if(student.getAsignaturas().size()!=0){
                for(int i = 0; i < student.getAsignaturas().size(); i++){
                    asignaturas.add(student.getAsignaturas().get(i).getIdAsignatura());
                }
            }
        }
        setAsignaturas(asignaturas);
    }
}
