package com.example.ex22.infrastructure.controller.dto.output;

import com.example.ex22.domain.Persona;

import java.time.LocalDate;

public record PersonaOutputDto (
        Integer idPersona,
        String user,
        String password,
        String name,
        String surname,
        String companyEmail,
        String personalEmail,
        String city,
        Boolean active,
        LocalDate createdDate,
        String imageUrl,
        LocalDate terminationDate
){ }