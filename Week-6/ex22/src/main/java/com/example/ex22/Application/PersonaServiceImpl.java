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
        /*List<PersonaOutputDto> personasOutputDto = new ArrayList<>();
        for (Persona persona : personas) {
            personasOutputDto.add(new PersonaOutputDto(persona));
        }*/
        List<PersonaOutputDto> personasOutputDto = personas.stream().map(p -> new PersonaOutputDto(p)).collect(Collectors.toList());
        return personasOutputDto;
    }

    @Override
    public PersonaOutputDto filterPersonaById(int id) throws Exception {
        Persona persona =
                personaRepositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No person with that ID"));
        PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
        return personaOutputDto;
    }

    @Override
    public List<PersonaOutputDto> filterPersonaByUser(String user) {
        List<Persona> personas = personaRepositorio.findByUser(user);
        if (personas.size() == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No person with that user");
        List<PersonaOutputDto> personasOutputDto = new ArrayList<>();
        for (Persona persona : personas) {
            personasOutputDto.add(new PersonaOutputDto(persona));
        }
        return personasOutputDto;
    }

    @Override
    public PersonaInputDto addPersona(PersonaInputDto personaInputDto) throws Exception {

        if (utils.checkLengthUsr(personaInputDto)) {
            throw new Exception("La longitud del usuario ha de estar entre 6 y 10");
        }
        if (personaInputDto.getPassword() == null || personaInputDto.getName() == null || personaInputDto.getCompanyEmail() == null
            || personaInputDto.getPersonalEmail() == null || personaInputDto.getCity() == null || personaInputDto.getActive() == null
            || personaInputDto.getCreatedDate() == null) {
            throw new Exception("Todos los campos son necesarios");
        }

        Persona persona = personaInputDtoToEntity(personaInputDto);
        personaRepositorio.saveAndFlush(persona);

        return personaInputDto;
    }

    @Override
    public PersonaOutputDto updatePersona(Integer id, PersonaInputDto personaInputDto) throws Exception {
        Persona persona =
                personaRepositorio
                        .findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "No person with that ID"));

        if (personaInputDto.getUser() != null) {
            if (utils.checkLengthUsr(personaInputDto)) {
                throw new Exception("La longitud del nombre de usuario no está entre 6 y 10");
            }

            persona.setUser(personaInputDto.getUser());
            persona.setPassword(personaInputDto.getPassword());
            persona.setName(personaInputDto.getName());
            persona.setSurname(personaInputDto.getSurname());
            persona.setCompanyEmail(personaInputDto.getCompanyEmail());
            persona.setPersonalEmail(personaInputDto.getPersonalEmail());
            persona.setCity(personaInputDto.getCity());
            persona.setActive(personaInputDto.getActive());
            persona.setCreatedDate(personaInputDto.getCreatedDate());
            persona.setImageUrl(personaInputDto.getImageUrl());
            persona.setTerminationDate(personaInputDto.getTerminationDate());
        }

        personaRepositorio.saveAndFlush(persona);
        PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
        return personaOutputDto;
    }

    @Override
    public PersonaOutputDto updatePatchPersona(Integer id, PersonaInputDto personaInputDto) throws Exception {
        Persona persona =
                personaRepositorio
                        .findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "No person with that ID"));

        if (personaInputDto.getUser() != null) {
        if (utils.checkLengthUsr(personaInputDto)) {
                throw new Exception("La longitud del nombre de usuario no está entre 6 y 10");
            }
            persona.setUser(personaInputDto.getUser());
        }

        if (personaInputDto.getPassword() != null) {
            persona.setPassword(personaInputDto.getPassword());
        }

        if (personaInputDto.getName() != null) {
            persona.setName(personaInputDto.getName());
        }

        if (personaInputDto.getSurname() != null) {
            persona.setSurname(personaInputDto.getSurname());
        }

        if (personaInputDto.getCompanyEmail() != null) {
            persona.setCompanyEmail(personaInputDto.getCompanyEmail());
        }

        if (personaInputDto.getPersonalEmail() != null) {
            persona.setPersonalEmail(personaInputDto.getPersonalEmail());
        }

        if (personaInputDto.getCity() != null) {
            persona.setCity(personaInputDto.getCity());
        }

        if (personaInputDto.getActive() != null) {
            persona.setActive(personaInputDto.getActive());
        }

        if (personaInputDto.getCreatedDate() != null) {
            persona.setCreatedDate(personaInputDto.getCreatedDate());
        }

        if (personaInputDto.getImageUrl() != null) {
            persona.setImageUrl(personaInputDto.getImageUrl());
        }

        if (personaInputDto.getTerminationDate() != null) {
            persona.setTerminationDate(personaInputDto.getTerminationDate());
        }

        personaRepositorio.saveAndFlush(persona);
        PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
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
        persona.setIdPersona(personaOutputDto.getIdPersona());
        persona.setUser(personaOutputDto.getUser());
        persona.setPassword(personaOutputDto.getPassword());
        persona.setName(personaOutputDto.getName());
        persona.setSurname(personaOutputDto.getSurname());
        persona.setCompanyEmail(personaOutputDto.getCompanyEmail());
        persona.setPersonalEmail(personaOutputDto.getPersonalEmail());
        persona.setCity(personaOutputDto.getCity());
        persona.setActive(personaOutputDto.getActive());
        persona.setCreatedDate(personaOutputDto.getCreatedDate());
        persona.setImageUrl(personaOutputDto.getImageUrl());
        persona.setTerminationDate(personaOutputDto.getTerminationDate());

        return persona;
    }

    private Persona personaInputDtoToEntity(PersonaInputDto personaInputDto) {
        Persona persona = new Persona();
        persona.setUser(personaInputDto.getUser());
        persona.setPassword(personaInputDto.getPassword());
        persona.setName(personaInputDto.getName());
        persona.setSurname(personaInputDto.getSurname());
        persona.setCompanyEmail(personaInputDto.getCompanyEmail());
        persona.setPersonalEmail(personaInputDto.getPersonalEmail());
        persona.setCity(personaInputDto.getCity());
        persona.setActive(personaInputDto.getActive());
        persona.setCreatedDate(personaInputDto.getCreatedDate());
        persona.setImageUrl(personaInputDto.getImageUrl());
        persona.setTerminationDate(personaInputDto.getTerminationDate());

        return persona;
    }
}
