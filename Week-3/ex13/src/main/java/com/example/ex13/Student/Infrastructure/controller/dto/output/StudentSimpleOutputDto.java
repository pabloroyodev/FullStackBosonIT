package com.example.ex13.Student.Infrastructure.controller.dto.output;

import com.example.ex13.Persona.Infrastructure.controller.dto.output.PersonaOutputDto;
import com.example.ex13.Student.Domain.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentSimpleOutputDto{

    private Integer idStudent;
    private Integer personaId;
    private Integer numHoursWeek;
    private String comments;
    private String branch;

    public StudentSimpleOutputDto(Student student){
        setIdStudent(student.getIdStudent());
        setPersonaId(student.getPersona().getIdPersona());
        setNumHoursWeek(student.getNumHoursWeek());
        setComments(student.getComments());
        setBranch(student.getBranch());
    }
}
