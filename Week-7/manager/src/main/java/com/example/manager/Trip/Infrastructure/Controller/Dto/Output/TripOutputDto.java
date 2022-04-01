package com.example.manager.Trip.Infrastructure.Controller.Dto.Output;

import com.example.manager.Trip.Domain.Trip;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data @NoArgsConstructor
public class TripOutputDto implements Serializable {
    private Integer idTrip;

    private Date date;

    private String departure;

    private String arrival;

    private Integer seats;

    private Integer deniedSeats;

    private boolean issue;

    private List<Integer> tickets;

    public TripOutputDto (Trip trip) {
        setIdTrip(trip.getIdTrip());
        setDate(trip.getDate());
        setDeparture(trip.getDeparture());
        setArrival(trip.getArrival());
        setSeats(trip.getSeats());
        setDeniedSeats(trip.getDeniedSeats());
        setIssue(trip.isIssue());

        List<Integer> tickets = new ArrayList<>();
        if(trip.getTickets() != null && trip.getTickets().size() != 0) {
            for(int i = 0; i < trip.getTickets().size(); i++){
                tickets.add(trip.getTickets().get(i).getIdTicket());
            }
        }
        setTickets(tickets);
    }
}
