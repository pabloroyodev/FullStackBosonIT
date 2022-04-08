package com.backweb.Ticket.Infrastructure.Controller.V0;

import com.backweb.Ticket.Application.TicketService;
import com.backweb.Ticket.Infrastructure.Controller.Dto.Output.TicketOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequestMapping("v0/ticket")
@RestController
public class ReadTicket {
    @Autowired
    TicketService ticketService;

    @GetMapping
    public List<TicketOutputDto> findAll(){
        return ticketService.getAllTickets();
    }

    @GetMapping("{id}")
    public TicketOutputDto filterTicketById(@PathVariable UUID id){
        return ticketService.filterTicketById(id);
    }
}
