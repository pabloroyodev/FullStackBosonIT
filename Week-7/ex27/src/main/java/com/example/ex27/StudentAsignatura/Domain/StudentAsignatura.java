package com.example.ex27.StudentAsignatura.Domain;

import com.example.ex27.Profesor.Domain.Profesor;
import com.example.ex27.Student.Domain.Student;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "asignaturas")
public class StudentAsignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_asignatura")
    private Integer idAsignatura;

    //Un Profesor puede impartir multiples asignaturas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_profesor")
    private Profesor profesor;

    //Estudiante puede tener multiples asignaturas
    @ManyToMany(mappedBy = "asignaturas")
    private List<Student> students;

    private String asignatura;

    private String comments;

    @NotNull
    private LocalDate initialDate;

    private LocalDate finishDate;

}
