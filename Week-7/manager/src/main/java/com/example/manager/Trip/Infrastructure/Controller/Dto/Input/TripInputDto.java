package com.example.manager.Trip.Infrastructure.Controller.Dto.Input;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data @NoArgsConstructor
public class TripInputDto implements Serializable {
    @NotNull
    private Date date;

    @NotNull
    private String departure;

    @NotNull
    private String arrival;

    @NotNull
    private Integer seats;

    @NotNull
    private boolean issue;

    @NotNull
    List<Integer> tickets;
}
