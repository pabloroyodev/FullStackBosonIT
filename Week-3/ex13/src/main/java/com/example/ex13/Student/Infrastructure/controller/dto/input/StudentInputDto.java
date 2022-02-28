package com.example.ex13.Student.Infrastructure.controller.dto.input;

import com.example.ex13.Persona.Domain.Persona;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class StudentInputDto implements Serializable {
    @NotNull
    private Persona persona;

    @NotNull
    private Integer numHoursWeek;

    private String comments;

    @NotNull
    private String branch;

    //TODO: Aqui anadiremos la lista de asignaturas posteriormente
}
