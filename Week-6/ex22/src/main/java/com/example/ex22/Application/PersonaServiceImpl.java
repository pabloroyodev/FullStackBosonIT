package com.example.ex22.Application;

import com.example.ex22.domain.Persona;
import com.example.ex22.infrastructure.controller.dto.input.PersonaInputDto;
import com.example.ex22.infrastructure.controller.dto.output.PersonaOutputDto;
import com.example.ex22.infrastructure.repository.jpa.PersonaRepositorio;
import com.example.ex22.utils.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepositorio personaRepositorio;

    @Override
    public List<PersonaOutputDto> getAllPersonas() {
        List<Persona> personas = personaRepositorio.findAll();
        List<PersonaOutputDto> personasOutputDto = personas.stream().map(persona -> new PersonaOutputDto(persona.getIdPersona(), persona.getUser(),persona.getPassword(), persona.getName(), persona.getSurname(), persona.getCompanyEmail(), persona.getPersonalEmail(),persona.getCity(), persona.getActive(), persona.getCreatedDate(),persona.getImageUrl(), persona.getTerminationDate())).collect(Collectors.toList());
        return personasOutputDto;
    }

    @Override
    public PersonaOutputDto filterPersonaById(int id) throws Exception {
        Persona persona =
                personaRepositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No person with that ID"));
        PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona.getIdPersona(), persona.getUser(),persona.getPassword(), persona.getName(), persona.getSurname(), persona.getCompanyEmail(), persona.getPersonalEmail(),persona.getCity(), persona.getActive(), persona.getCreatedDate(),persona.getImageUrl(), persona.getTerminationDate());
        return personaOutputDto;
    }

    @Override
    public List<PersonaOutputDto> filterPersonaByUser(String user) {
        List<Persona> personas = personaRepositorio.findByUser(user);
        if (personas.size() == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No person with that user");
        List<PersonaOutputDto> personasOutputDto = new ArrayList<>();
        for (Persona persona : personas) {
            personasOutputDto.add(new PersonaOutputDto(persona.getIdPersona(), persona.getUser(),persona.getPassword(), persona.getName(), persona.getSurname(), persona.getCompanyEmail(), persona.getPersonalEmail(),persona.getCity(), persona.getActive(), persona.getCreatedDate(),persona.getImageUrl(), persona.getTerminationDate()));
        }
        return personasOutputDto;
    }

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto personaInputDto) throws Exception {

        if (utils.checkLengthUsr(personaInputDto)) {
            throw new Exception("La longitud del usuario ha de estar entre 6 y 10");
        }
        if (personaInputDto.password() == null || personaInputDto.name() == null || personaInputDto.companyEmail() == null
            || personaInputDto.personalEmail() == null || personaInputDto.city() == null || personaInputDto.active() == null
            || personaInputDto.createdDate() == null) {
            throw new Exception("Todos los campos son necesarios");
        }

        Persona persona = personaInputDtoToEntity(personaInputDto);
        personaRepositorio.saveAndFlush(persona);
        PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona.getIdPersona(), persona.getUser(),persona.getPassword(), persona.getName(), persona.getSurname(), persona.getCompanyEmail(), persona.getPersonalEmail(),persona.getCity(), persona.getActive(), persona.getCreatedDate(),persona.getImageUrl(), persona.getTerminationDate());
        return personaOutputDto;
    }

    @Override
    public PersonaOutputDto updatePersona(Integer id, PersonaInputDto personaInputDto) throws Exception {
        Persona persona =
                personaRepositorio
                        .findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "No person with that ID"));

        if (personaInputDto.user() != null) {
            if (utils.checkLengthUsr(personaInputDto)) {
                throw new Exception("La longitud del nombre de usuario no está entre 6 y 10");
            }

            persona.setUser(personaInputDto.user());
            persona.setPassword(personaInputDto.password());
            persona.setName(personaInputDto.name());
            persona.setSurname(personaInputDto.surname());
            persona.setCompanyEmail(personaInputDto.companyEmail());
            persona.setPersonalEmail(personaInputDto.personalEmail());
            persona.setCity(personaInputDto.city());
            persona.setActive(personaInputDto.active());
            persona.setCreatedDate(personaInputDto.createdDate());
            persona.setImageUrl(personaInputDto.imageUrl());
            persona.setTerminationDate(personaInputDto.terminationDate());
        }

        personaRepositorio.saveAndFlush(persona);
        PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona.getIdPersona(), persona.getUser(),persona.getPassword(), persona.getName(), persona.getSurname(), persona.getCompanyEmail(), persona.getPersonalEmail(),persona.getCity(), persona.getActive(), persona.getCreatedDate(),persona.getImageUrl(), persona.getTerminationDate());
        return personaOutputDto;
    }

    @Override
    public PersonaOutputDto updatePatchPersona(Integer id, PersonaInputDto personaInputDto) throws Exception {
        Persona persona =
                personaRepositorio
                        .findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "No person with that ID"));

        if (personaInputDto.user() != null) {
        if (utils.checkLengthUsr(personaInputDto)) {
                throw new Exception("La longitud del nombre de usuario no está entre 6 y 10");
            }
            persona.setUser(personaInputDto.user());
        }

        if (personaInputDto.password() != null) {
            persona.setPassword(personaInputDto.password());
        }

        if (personaInputDto.name() != null) {
            persona.setName(personaInputDto.name());
        }

        if (personaInputDto.surname() != null) {
            persona.setSurname(personaInputDto.surname());
        }

        if (personaInputDto.companyEmail() != null) {
            persona.setCompanyEmail(personaInputDto.companyEmail());
        }

        if (personaInputDto.personalEmail() != null) {
            persona.setPersonalEmail(personaInputDto.personalEmail());
        }

        if (personaInputDto.city() != null) {
            persona.setCity(personaInputDto.city());
        }

        if (personaInputDto.active() != null) {
            persona.setActive(personaInputDto.active());
        }

        if (personaInputDto.createdDate() != null) {
            persona.setCreatedDate(personaInputDto.createdDate());
        }

        if (personaInputDto.imageUrl() != null) {
            persona.setImageUrl(personaInputDto.imageUrl());
        }

        if (personaInputDto.terminationDate() != null) {
            persona.setTerminationDate(personaInputDto.terminationDate());
        }

        personaRepositorio.saveAndFlush(persona);
        PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona.getIdPersona(), persona.getUser(),persona.getPassword(), persona.getName(), persona.getSurname(), persona.getCompanyEmail(), persona.getPersonalEmail(),persona.getCity(), persona.getActive(), persona.getCreatedDate(),persona.getImageUrl(), persona.getTerminationDate());
        return personaOutputDto;
    }

    @Override
    public void deletePersona(Integer id) throws Exception {
        personaRepositorio.delete(
                personaRepositorio
                        .findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "No person with that ID")));
    }

    private Persona personaOutputDtoToEntity(PersonaOutputDto personaOutputDto) {
        Persona persona = new Persona();
        persona.setIdPersona(personaOutputDto.idPersona());
        persona.setUser(personaOutputDto.user());
        persona.setPassword(personaOutputDto.password());
        persona.setName(personaOutputDto.name());
        persona.setSurname(personaOutputDto.surname());
        persona.setCompanyEmail(personaOutputDto.companyEmail());
        persona.setPersonalEmail(personaOutputDto.personalEmail());
        persona.setCity(personaOutputDto.city());
        persona.setActive(personaOutputDto.active());
        persona.setCreatedDate(personaOutputDto.createdDate());
        persona.setImageUrl(personaOutputDto.imageUrl());
        persona.setTerminationDate(personaOutputDto.terminationDate());

        return persona;
    }

    private Persona personaInputDtoToEntity(PersonaInputDto personaInputDto) {
        Persona persona = new Persona();
        persona.setUser(personaInputDto.user());
        persona.setPassword(personaInputDto.password());
        persona.setName(personaInputDto.name());
        persona.setSurname(personaInputDto.surname());
        persona.setCompanyEmail(personaInputDto.companyEmail());
        persona.setPersonalEmail(personaInputDto.personalEmail());
        persona.setCity(personaInputDto.city());
        persona.setActive(personaInputDto.active());
        persona.setCreatedDate(personaInputDto.createdDate());
        persona.setImageUrl(personaInputDto.imageUrl());
        persona.setTerminationDate(personaInputDto.terminationDate());

        return persona;
    }
}
