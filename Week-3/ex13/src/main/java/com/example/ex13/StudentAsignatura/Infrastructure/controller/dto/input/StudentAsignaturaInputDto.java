package com.example.ex13.StudentAsignatura.Infrastructure.controller.dto.input;

import com.example.ex13.Profesor.Infrastructure.controller.dto.output.ProfesorOutputDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class StudentAsignaturaInputDto implements Serializable {

    @NotNull
    private Integer idProfesor;

    //TODO: Relacion de multiples estudiantes matriculados en asignaturas.

    private String asignatura;

    private String comments;

    @NotNull
    private LocalDate initialDate;

    private LocalDate finishDate;
}
