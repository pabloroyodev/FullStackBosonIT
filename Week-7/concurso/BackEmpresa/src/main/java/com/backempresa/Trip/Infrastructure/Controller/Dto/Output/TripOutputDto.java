package com.backempresa.Trip.Infrastructure.Controller.Dto.Output;

import com.backempresa.Trip.Domain.Trip;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data @NoArgsConstructor
public class TripOutputDto implements Serializable {
    private UUID idTrip;

    private Date date;

    private String departure;

    private String arrival;

    private Integer seats;

    private Integer deniedSeats;

    private boolean issue;

    private List<UUID> tickets;

    public TripOutputDto (Trip trip) {
        setIdTrip(trip.getIdTrip());
        setDate(trip.getDate());
        setDeparture(trip.getDeparture());
        setArrival(trip.getArrival());
        setSeats(trip.getSeats());
        setDeniedSeats(trip.getDeniedSeats());
        setIssue(trip.isIssue());

        List<UUID> ticketList = new ArrayList<>();
        if(trip.getTickets() != null && trip.getTickets().size() != 0) {
            for(int i = 0; i < trip.getTickets().size(); i++){
                ticketList.add(trip.getTickets().get(i).getIdTicket());
            }
        }
        setTickets(ticketList);
    }
}
