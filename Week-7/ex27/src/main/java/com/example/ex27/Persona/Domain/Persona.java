package com.example.ex27.Persona.Domain;

import com.example.ex27.Profesor.Domain.Profesor;
import com.example.ex27.Student.Domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_persona")
    private Integer idPersona;

    private String user;

    private String password;

    private String name;

    private String surname;

    @Column(name = "company_email")
    private String companyEmail;

    @Column(name = "personal_email")
    private String personalEmail;

    private String city;

    private Boolean active;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "imagen_url")
    private String imageUrl;

    @Column(name = "termination_date")
    private LocalDate terminationDate;

    //Una persona tiene asignado un estudiante (o profesor)
    @OneToOne(mappedBy = "persona", fetch = FetchType.LAZY)
    private Student student;

    //Una persona tiene asignado un profesor (o estudiante)
    @OneToOne(mappedBy = "persona", fetch = FetchType.LAZY)
    private Profesor profesor;

    @NotNull
    private Boolean admin;
}
