package com.example.ex24.Student.Infrastructure.controller.dto.output;

import com.example.ex24.Persona.Infrastructure.controller.dto.output.PersonaOutputDto;
import com.example.ex24.Student.Domain.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class StudentFullOutputDto extends StudentSimpleOutputDto{
    @NotNull
    private PersonaOutputDto personaOutputDto;

    public StudentFullOutputDto(Student student){
        super(student);
        setPersonaOutputDto(new PersonaOutputDto(student.getPersona()));
    }
}
