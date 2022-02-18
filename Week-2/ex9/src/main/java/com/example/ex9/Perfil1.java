package com.example.ex9;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("perfil1")
public class Perfil1 implements Perfiles {

    private final String perfil = "perfil1";

    public String miFuncion() {
        return perfil;
    }
}
