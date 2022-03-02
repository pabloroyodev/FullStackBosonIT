package com.example.ex16.StudentAsignatura.Infrastructure.controller.dto.output;

import com.example.ex16.Profesor.Infrastructure.controller.dto.output.ProfesorOutputDto;
import com.example.ex16.StudentAsignatura.Domain.StudentAsignatura;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StudentAsignaturaOutputDto implements Serializable {
    @NotNull
    private Integer idAsignatura;

    @NotNull
    private ProfesorOutputDto profesorOutputDto;

    private List<Integer> students;

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

        List<Integer> students = new ArrayList<>();
        if(studentAsignatura.getStudents().size()!=0){
            for(int i = 0; i < studentAsignatura.getStudents().size(); i++){
                students.add(studentAsignatura.getStudents().get(i).getIdStudent());
            }
        }
        setStudents(students);

        setComments(studentAsignatura.getComments());
        setInitialDate(studentAsignatura.getInitialDate());
        setFinishDate(studentAsignatura.getFinishDate());
    }
}
