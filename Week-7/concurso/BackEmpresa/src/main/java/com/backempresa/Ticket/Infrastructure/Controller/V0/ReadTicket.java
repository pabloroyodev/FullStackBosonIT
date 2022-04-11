package com.backempresa.Ticket.Infrastructure.Controller.V0;

import com.backempresa.Ticket.Application.TicketService;
import com.backempresa.Ticket.Infrastructure.Controller.Dto.Output.TicketOutputDto;
import com.backempresa.Ticket.Infrastructure.Repository.TicketRepository;
import com.backempresa.Utils.Auth.AuthUtils;
import com.backempresa.Utils.Exceptions.customUnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("v0/ticket")
@RestController
public class ReadTicket {
    @Autowired
    TicketService ticketService;

    @Autowired
    TicketRepository ticketRepository;

    @GetMapping
    public List<TicketOutputDto> findAll(){
        return ticketService.getAllTickets();
    }

    @GetMapping("{id}")
    public TicketOutputDto filterTicketById(@PathVariable UUID id, @RequestHeader("Authorization") String auth) {
        UUID idToken = AuthUtils.getId(auth);
        if (!idToken.equals(ticketRepository.findById(id).orElseThrow().getClient().getIdClient())) {
            throw new customUnprocesableException("La persona autenticada no corresponde con al persona de la que quieres leer el ticket");
        }

        return ticketService.filterTicketById(id);
    }
}
