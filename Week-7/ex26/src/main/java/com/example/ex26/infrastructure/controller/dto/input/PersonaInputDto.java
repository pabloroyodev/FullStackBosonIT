package com.example.ex26.infrastructure.controller.dto.input;

import com.example.ex26.domain.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaInputDto implements Serializable {
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

    public PersonaInputDto(Persona persona){
        if (persona == null) {
            return;
        }

        setUser(persona.getUser());
        setPassword(persona.getPassword());
        setName(persona.getName());
        setSurname(persona.getSurname());
        setCompanyEmail(persona.getCompanyEmail());
        setPersonalEmail(persona.getPersonalEmail());
        setCity(persona.getCity());
        setActive(persona.getActive());
        setCreatedDate(persona.getCreatedDate());
        setImageUrl(persona.getImageUrl());
        setTerminationDate(persona.getTerminationDate());
    }
}
