package com.example.manager.Trip.Infrastructure.Controller.Dto.Input;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data @NoArgsConstructor
public class TripInputDto implements Serializable {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
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

}
