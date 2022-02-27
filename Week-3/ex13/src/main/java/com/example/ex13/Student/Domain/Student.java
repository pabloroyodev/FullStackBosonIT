package com.example.ex13.Student.Domain;

import com.example.ex13.Persona.Domain.Persona;
import com.example.ex13.Profesor.Domain.Profesor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_student")
    private String idStudent;

    @OneToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "fk_persona")
    private Persona persona;

    @Column(name = "num_hours_week", nullable = false)
    private Integer numHoursWeek;

    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_profesor")
    private Profesor profesor;

    @NotNull
    private String branch;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "student_asignatura",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "asignatura_id")}
    )
    List<String> asignaturas;

}
