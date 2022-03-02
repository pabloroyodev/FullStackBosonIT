package com.example.ex16.Profesor.Infrastructure.controller.dto.output;

import com.example.ex16.Persona.Infrastructure.controller.dto.output.PersonaOutputDto;
import com.example.ex16.Profesor.Domain.Profesor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ProfesorOutputDto implements Serializable {
    private Integer idProfesor;

    @NotNull
    private PersonaOutputDto personaOutputDto;

    private String comments;

    private String branch;

    public ProfesorOutputDto(Profesor profesor){
        if (profesor == null) {
            System.out.println("La clase profesor esta incompleta");
            return;
        }
        setIdProfesor(profesor.getIdProfesor());
        setPersonaOutputDto(new PersonaOutputDto(profesor.getPersona()));
        setComments(profesor.getComments());
        setBranch(profesor.getBranch());
    }
}
