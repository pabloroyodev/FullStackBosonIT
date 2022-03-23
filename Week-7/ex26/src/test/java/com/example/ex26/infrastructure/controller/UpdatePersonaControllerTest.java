package com.example.ex26.infrastructure.controller;

import com.example.ex26.Application.PersonaService;
import com.example.ex26.infrastructure.controller.dto.input.PersonaInputDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static com.example.ex26.infrastructure.controller.personaInput.assertionsPersona;
import static com.example.ex26.infrastructure.controller.personaInput.createPersona;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UpdatePersonaControllerTest {

    @Autowired
    private PersonaService personaService;

    @BeforeAll
    void setUp() throws Exception {
        PersonaInputDto personaC = new PersonaInputDto();
        createPersona(personaC, "hola");
        personaService.addPersona(personaC);
    }

    @Test
    @DisplayName("Actualizar persona")
    void updatePersona() throws Exception {
        PersonaInputDto personaD = new PersonaInputDto("Cambiad","321","nuevo","apellidonuevo","email","emailpersonal","nuevaciudad",true, LocalDate.now(),"imgNueva",LocalDate.now());
        assertionsPersona(personaD, personaService.updatePersona(2,personaD));
    }

    @Test
    @DisplayName("Actualizar persona con username demasiado largo (Throw Exception)")
    void updatePersonaThrowException() {
        Assertions.assertThrows(Exception.class, () -> {
          PersonaInputDto personaA = new PersonaInputDto();
          createPersona(personaA, "UserNameLargo");
          personaService.updatePersona(0, personaA);
        });
    }
}