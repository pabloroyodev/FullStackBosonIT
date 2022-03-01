package com.example.ex13.StudentAsignatura.Infrastructure.controller.dto.output;

import com.example.ex13.Profesor.Infrastructure.controller.dto.output.ProfesorOutputDto;
import com.example.ex13.StudentAsignatura.Domain.StudentAsignatura;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class StudentAsignaturaOutputDto implements Serializable {
    @NotNull
    private Integer idAsignatura;

    @NotNull
    private ProfesorOutputDto profesorOutputDto;

    //TODO: Relacion de multiples estudiantes matriculados en asignaturas.

    private String asignatura;

    private String comments;

    @NotNull
    private LocalDate initialDate;

    private LocalDate finishDate;


    public StudentAsignaturaOutputDto(StudentAsignatura studentAsignatura){
        if (studentAsignatura == null) {
            System.out.println("La asignatura esta incompleta");
            return;
        }
        setIdAsignatura(studentAsignatura.getIdAsignatura());
        setProfesorOutputDto(new ProfesorOutputDto(studentAsignatura.getProfesor()));
        setAsignatura(studentAsignatura.getAsignatura());
        //TODO: Aqui anadiremos la relacion de estudiantes matriculados
        setComments(studentAsignatura.getComments());
        setInitialDate(studentAsignatura.getInitialDate());
        setFinishDate(studentAsignatura.getFinishDate());
    }
}
