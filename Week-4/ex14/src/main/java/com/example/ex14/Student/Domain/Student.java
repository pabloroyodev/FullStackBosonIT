package com.example.ex14.Student.Domain;

import com.example.ex14.Persona.Domain.Persona;
import com.example.ex14.StudentAsignatura.Domain.StudentAsignatura;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_student")
    private Integer idStudent;

    //El estudiante tiene los datos de su correspondiente persona
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_persona", unique = true)
    private Persona persona;

    @Column(name = "num_hours_week", nullable = false)
    private Integer numHoursWeek;

    private String comments;

    @NotNull
    private String branch;

    //Creamos la tabla para (N:M) llamada "student_asignatura" para
    //relacionar los multiples estudiantes estudiando multiples asignaturas.
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "student_asignatura",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "asignatura_id")}
    )
    List<StudentAsignatura> asignaturas;
}
