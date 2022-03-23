package com.example.ex26.infrastructure.controller;

import com.example.ex26.Application.PersonaService;
import com.example.ex26.infrastructure.controller.dto.input.PersonaInputDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.ex26.infrastructure.controller.personaInput.assertionsPersona;
import static com.example.ex26.infrastructure.controller.personaInput.createPersona;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreatePersonaControllerTest {

    @Autowired
    private PersonaService personaService;

    @Test
    @DisplayName("Testing add person")
    void addPersonaCorrecta() throws Exception{
        PersonaInputDto personaA = new PersonaInputDto();
        createPersona(personaA, "hola");
        assertionsPersona(personaA, personaService.addPersona(personaA));
    }

    //El usuario es demasiado largo (+10 caracteres) y ha de saltar excepcion
    @Test
    @DisplayName("Testing add person with username very long (Throw Exception)")
    void addPersonaThrowException() {
        PersonaInputDto personaB = new PersonaInputDto();
        createPersona(personaB, "UsuarioMuyLargoooo");

        Assertions.assertThrows(Exception.class, () -> {
            personaService.addPersona(personaB);
        });
    }
}