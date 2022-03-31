package com.example.manager.Ticket.Infrastructure.Controller.V0;

import com.example.manager.Ticket.Application.TicketService;
import com.example.manager.Ticket.Infrastructure.Controller.Dto.Input.TicketInputDto;
import com.example.manager.Ticket.Infrastructure.Controller.Dto.Output.TicketOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("v0/ticket")
@RestController
public class CreateTicket {
    @Autowired
    TicketService ticketService;

    @PostMapping
    public TicketOutputDto addTicket(@RequestBody TicketInputDto ticketInputDto) throws Exception {
        return ticketService.addTicket(ticketInputDto);
    }
}
