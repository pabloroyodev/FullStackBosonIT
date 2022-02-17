package com.example.ex5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador")
public class Controlador {
    @Autowired
    @Qualifier("bean1")
    PersonaService PersonaBean1;

    @Autowired
    @Qualifier("bean2")
    PersonaService PersonaBean2;

    @Autowired
    @Qualifier("bean3")
    PersonaService PersonaBean3;

    @GetMapping("/bean/{bean}")
    public Persona devolverBean(@PathVariable String bean) {
        switch (bean) {
            case "bean1":
                return PersonaBean1.getPersona();
            case "bean2":
                return PersonaBean2.getPersona();
            case "bean3":
                return PersonaBean3.getPersona();
        }
        return null;
    }
}
