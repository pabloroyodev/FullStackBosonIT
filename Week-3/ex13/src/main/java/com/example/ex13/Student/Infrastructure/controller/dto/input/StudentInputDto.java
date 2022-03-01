package com.example.ex13.Student.Infrastructure.controller.dto.input;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class StudentInputDto implements Serializable {
    @NotNull
    private Integer idPersona;

    @NotNull
    private Integer numHoursWeek;

    private String comments;

    @NotNull
    private String branch;

    @NonNull
    List<Integer> asignaturas;
}
