package com.example.manager.Ticket.Infrastructure.Controller.V0;

import com.example.manager.Ticket.Application.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("v0/ticket")
@RestController
public class DeleteTicket {
    @Autowired
    TicketService ticketService;

    @DeleteMapping("{id}")
    public void deleteTicket(@PathVariable Integer id) {
        ticketService.deleteTicket(id);
    }
}
