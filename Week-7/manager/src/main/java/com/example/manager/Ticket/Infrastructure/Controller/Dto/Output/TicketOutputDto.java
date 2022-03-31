package com.example.manager.Ticket.Infrastructure.Controller.Dto.Output;

import com.example.manager.Client.Domain.Client;
import com.example.manager.Ticket.Domain.Ticket;
import com.example.manager.Trip.Domain.Trip;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @NoArgsConstructor
public class TicketOutputDto implements Serializable {
    private Integer idTicket;

    private String details;

    private Client client;

    private Trip trip;

    public TicketOutputDto (Ticket ticket){
        setIdTicket(ticket.getIdTicket());
        setDetails(ticket.getDetails());
        setClient(ticket.getClient());
        setTrip(ticket.getTrip());
    }
}
