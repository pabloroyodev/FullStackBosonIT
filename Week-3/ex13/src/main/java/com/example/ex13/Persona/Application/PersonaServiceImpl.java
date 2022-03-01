package com.example.ex13.Persona.Application;

import com.example.ex13.Persona.Infrastructure.controller.dto.input.PersonaInputDto;
import com.example.ex13.Persona.Infrastructure.controller.dto.output.PersonaOutputDto;
import com.example.ex13.Persona.Infrastructure.repository.jpa.PersonaRepositorio;
import com.example.ex13.Persona.Domain.Persona;
import com.example.ex13.exceptions.NotFoundException;
import com.example.ex13.exceptions.UnprocesableException;
import com.example.ex13.utils.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        /* MODO ANTIGUO DE ITERAR EN TODA LA LISTA DE PERSONAS:
        List<PersonaOutputDto> personasOutputDto = new ArrayList<>();
        for (Persona persona : personas) {
            personasOutputDto.add(new PersonaOutputDto(persona));
        }
        NUEVO MODO DE ITERAR:*/
        List<PersonaOutputDto> personasOutputDto = personas.stream().map(p -> new PersonaOutputDto(p)).collect(Collectors.toList());
        return personasOutputDto;
    }

    @Override
    public PersonaOutputDto filterPersonaById(int id) throws Exception {
        Persona persona =
                personaRepositorio.findById(id).orElseThrow(() -> new NotFoundException(id + " not found."));
        PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
        return personaOutputDto;
    }

    @Override
    public List<PersonaOutputDto> filterPersonaByUser(String user) {
        List<Persona> personas = personaRepositorio.findByUser(user);
        if (personas.size() == 0) throw new NotFoundException(user + "not found.");
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
            throw new UnprocesableException("Faltan campos por introducir");
        }

        Persona persona = personaInputDtoToEntity(personaInputDto);
        personaRepositorio.save(persona);

        return personaInputDto;
    }

    @Override
    public PersonaOutputDto updatePersona(Integer id, PersonaInputDto personaInputDto) throws Exception {
        Persona persona =
                personaRepositorio
                        .findById(id)
                        .orElseThrow(() -> new UnprocesableException(id + " not found"));

        if (personaInputDto.getUser() != null) {
            if (utils.checkLengthUsr(personaInputDto)) {
                throw new UnprocesableException("La longitud del usuario no está entre 6 y 10");
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

        personaRepositorio.save(persona);
        PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
        return personaOutputDto;
    }

    @Override
    public PersonaOutputDto updatePatchPersona(Integer id, PersonaInputDto personaInputDto) throws Exception {
        Persona persona =
                personaRepositorio
                        .findById(id)
                        .orElseThrow(() -> new UnprocesableException(id + " not found"));

        if (personaInputDto.getUser() != null) {
        if (utils.checkLengthUsr(personaInputDto)) {
                throw new UnprocesableException("La longitud del usuario no está entre 6 y 10");
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

        personaRepositorio.save(persona);
        PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
        return personaOutputDto;
    }

    @Override
    public void deletePersona(Integer id) throws Exception {
        personaRepositorio.delete(
                personaRepositorio
                        .findById(id)
                        .orElseThrow(() -> new UnprocesableException(id + " not found")));
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
