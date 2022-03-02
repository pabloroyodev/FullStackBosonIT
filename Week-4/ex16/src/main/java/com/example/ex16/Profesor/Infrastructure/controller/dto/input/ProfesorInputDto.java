package com.example.ex16.Profesor.Infrastructure.controller.dto.input;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ProfesorInputDto implements Serializable {
    @NonNull
    private Integer idPersona;

    private String comments;

    @NonNull
    private String branch;
}
