package com.example.ex22.infrastructure.controller.dto.input;

import java.time.LocalDate;

public record PersonaInputDto (
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
