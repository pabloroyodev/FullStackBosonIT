package com.example.ex5;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public @Data class Ciudad {
    private String nombre;
    private int numeroHabitantes;

    public Ciudad() {
        this.nombre = null;
        this.numeroHabitantes = -1;
    }

    public Ciudad(String nombre, int numeroHabitantes) {
        this.nombre = nombre;
        this.numeroHabitantes = numeroHabitantes;
    }
}