package com.example.ex24.StudentAsignatura.Infrastructure.controller.dto.input;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class StudentAsignaturaInputDto implements Serializable {

    @NotNull
    private Integer idProfesor;

    @NotNull
    private List<Integer> students;

    private String asignatura;

    private String comments;

    @NotNull
    private LocalDate initialDate;

    private LocalDate finishDate;
}
