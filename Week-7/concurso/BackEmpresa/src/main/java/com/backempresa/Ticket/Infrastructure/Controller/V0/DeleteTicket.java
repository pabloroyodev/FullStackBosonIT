package com.backempresa.Ticket.Infrastructure.Controller.V0;

import com.backempresa.Ticket.Application.TicketService;
import com.backempresa.Ticket.Infrastructure.Repository.TicketRepository;
import com.backempresa.Utils.Auth.AuthUtils;
import com.backempresa.Utils.Exceptions.customUnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("v0-empresa/ticket")
@RestController
public class DeleteTicket {
    @Autowired
    TicketService ticketService;

    @Autowired
    TicketRepository ticketRepository;

    @DeleteMapping("{id}")
    public void deleteTicket(@PathVariable UUID id, @RequestHeader("Authorization") String auth) {

        UUID idToken = AuthUtils.getId(auth);
        if (!idToken.equals(ticketRepository.findById(id).orElseThrow().getClient().getIdClient())) {
            throw new customUnprocesableException("No puedes eliminar un ticket de otra persona, solo el tuyo");
        }

        ticketService.deleteTicket(id);
    }
}
