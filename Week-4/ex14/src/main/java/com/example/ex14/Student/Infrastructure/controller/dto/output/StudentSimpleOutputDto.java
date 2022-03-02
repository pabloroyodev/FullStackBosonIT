package com.example.ex14.Student.Infrastructure.controller.dto.output;

import com.example.ex14.Student.Domain.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StudentSimpleOutputDto{

    private Integer idStudent;
    private Integer personaId;
    private Integer numHoursWeek;
    private String comments;
    private String branch;
    List<Integer> asignaturas;

    public StudentSimpleOutputDto(Student student){
        setIdStudent(student.getIdStudent());
        setPersonaId(student.getPersona().getIdPersona());
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
