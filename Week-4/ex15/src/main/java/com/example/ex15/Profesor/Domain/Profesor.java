package com.example.ex15.Profesor.Domain;

import com.example.ex15.StudentAsignatura.Domain.StudentAsignatura;
import com.example.ex15.Persona.Domain.Persona;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_profesor")
    private Integer idProfesor;

    @OneToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "fk_persona", unique = true)
    private Persona persona;

    private String comments;

    @NotNull
    private String branch;

    //Un profesor puede impartir multiples asignaturas (solo 1 profe por asignatura).
    @OneToMany(mappedBy = "profesor")
    private List<StudentAsignatura> asignaturas;
}
