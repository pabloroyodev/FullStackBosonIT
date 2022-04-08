package com.backweb.Ticket.Infrastructure.Controller.V0;

import com.backweb.Ticket.Application.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("v0/ticket")
@RestController
public class DeleteTicket {
    @Autowired
    TicketService ticketService;

    @DeleteMapping("{id}")
    public void deleteTicket(@PathVariable UUID id) {
        ticketService.deleteTicket(id);
    }
}
