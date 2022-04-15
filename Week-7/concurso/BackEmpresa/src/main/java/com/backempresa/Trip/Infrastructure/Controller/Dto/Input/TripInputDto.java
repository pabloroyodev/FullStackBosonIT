package com.backempresa.Trip.Infrastructure.Controller.Dto.Input;

import lombok.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data @NoArgsConstructor
public class TripInputDto implements Serializable {

    @NonNull
    private Date date;

    @NonNull
    private String departure;

    @NonNull
    private String arrival;

    @NonNull
    private boolean issue;

}
