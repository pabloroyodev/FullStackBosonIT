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

    private Integer idClient;

    private Integer idTrip;

    public TicketOutputDto (Ticket ticket){
        setIdTicket(ticket.getIdTicket());
        setDetails(ticket.getDetails());
        setIdClient(ticket.getClient().getIdClient());
        setIdTrip(ticket.getTrip().getIdTrip());
    }
}
