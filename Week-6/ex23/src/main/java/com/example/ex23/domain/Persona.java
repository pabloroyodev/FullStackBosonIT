package com.example.ex23.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "createdDate")
    private LocalDate createdDate;

    @Column(name = "imagen_url")
    private String imageUrl;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "termination_date")
    private LocalDate terminationDate;
}
