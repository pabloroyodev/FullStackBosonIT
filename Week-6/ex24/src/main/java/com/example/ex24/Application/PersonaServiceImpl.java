package com.example.ex24.Application;

import com.example.ex24.domain.Persona;
import com.example.ex24.exceptions.NotFoundException;
import com.example.ex24.exceptions.UnprocesableException;
import com.example.ex24.infrastructure.controller.dto.input.PersonaInputDto;
import com.example.ex24.infrastructure.controller.dto.output.PersonaOutputDto;
import com.example.ex24.utils.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<PersonaOutputDto> getAllPersonas() {
        List<Persona> personas = mongoTemplate.findAll(Persona.class);
        List<PersonaOutputDto> personasOutputDto = personas.stream().map(p -> new PersonaOutputDto(p)).collect(Collectors.toList());
        return personasOutputDto;
    }

    @Override
    public PersonaOutputDto filterPersonaById(String id) throws NotFoundException {
        Persona persona = mongoTemplate.findById(id,Persona.class);
        PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
        return personaOutputDto;
    }

    @Override
    public List<Persona> filterPersonaByUser(String user) {

        Query query = new Query();
        query.addCriteria(Criteria.where("user").is(user));
        return mongoTemplate.find(query, Persona.class);
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
        mongoTemplate.save(persona);
        return personaInputDto;
    }

    @Override
    public PersonaOutputDto updatePersona(String id, PersonaInputDto personaInputDto) throws NotFoundException {
        Persona persona = mongoTemplate.findById(id, Persona.class);

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

        mongoTemplate.save(persona);
        PersonaOutputDto personaOutputDto = new PersonaOutputDto(persona);
        return personaOutputDto;
    }

    @Override
    public void deletePersona(String id) throws UnprocesableException {
        Persona persona = mongoTemplate.findById(id, Persona.class);
        mongoTemplate.remove(persona);
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
