package com.example.ex24.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "Persona")
@Data
@NoArgsConstructor
public class Persona {
    @Id
    private String idPersona;

    private String user;

    private String password;

    private String name;

    private String surname;

    private String companyEmail;

    private String personalEmail;

    private String city;

    private Boolean active;

    private LocalDate createdDate;

    private String imageUrl;

    private LocalDate terminationDate;
}
