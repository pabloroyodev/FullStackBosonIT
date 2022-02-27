package com.example.ex13.Profesor.Domain;

import com.example.ex13.StudentAsignatura.Domain.StudentAsignatura;
import com.example.ex13.Persona.Domain.Persona;
import com.example.ex13.Student.Domain.Student;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_profesor")
    private String idProfesor;

    @OneToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "fk_persona")
    private Persona persona;

    private String comments;

    @NotNull
    private String branch;

    @OneToMany(mappedBy = "profesor") //
    private List<Student> students;

    @OneToMany(mappedBy = "profesor")
    private List<StudentAsignatura> asignaturas;
}
