package com.backempresa.Trip.Infrastructure.Controller.Dto.Output;

import com.backempresa.Trip.Domain.Trip;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

//No se devuelve ni los tickets denegados ni el array con los ID tickets reserva

@Data
@NoArgsConstructor
public class TripCensoredOutputDto implements Serializable {
    private UUID idTrip;

    private Date date;

    private String departure;

    private String arrival;

    private Integer seats;

    private boolean issue;

    public TripCensoredOutputDto (Trip trip) {
        setIdTrip(trip.getIdTrip());
        setDate(trip.getDate());
        setDeparture(trip.getDeparture());
        setArrival(trip.getArrival());
        setSeats(trip.getSeats());
        setIssue(trip.isIssue());
    }
}
