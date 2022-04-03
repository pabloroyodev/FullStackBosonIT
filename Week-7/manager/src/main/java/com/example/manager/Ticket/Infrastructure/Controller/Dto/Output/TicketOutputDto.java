package com.example.manager.Ticket.Infrastructure.Controller.Dto.Output;

import com.example.manager.Ticket.Domain.Ticket;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data @NoArgsConstructor
public class TicketOutputDto implements Serializable {
    private UUID idTicket;

    private String details;

    private UUID idClient;

    private UUID idTrip;

    public TicketOutputDto (Ticket ticket){
        setIdTicket(ticket.getIdTicket());
        setDetails(ticket.getDetails());
        setIdClient(ticket.getClient().getIdClient());
        setIdTrip(ticket.getTrip().getIdTrip());
    }
}
