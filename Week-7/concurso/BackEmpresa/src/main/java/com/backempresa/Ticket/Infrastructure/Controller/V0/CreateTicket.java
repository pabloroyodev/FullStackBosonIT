package com.backempresa.Ticket.Infrastructure.Controller.V0;

import com.backempresa.Ticket.Application.TicketService;
import com.backempresa.Ticket.Infrastructure.Controller.Dto.Input.TicketInputDto;
import com.backempresa.Ticket.Infrastructure.Controller.Dto.Output.TicketOutputDto;
import com.backempresa.Utils.Auth.AuthUtils;
import com.backempresa.Utils.Exceptions.customUnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("v0-empresa/ticket")
@RestController
public class CreateTicket {
    @Autowired
    TicketService ticketService;

    @PostMapping
    public TicketOutputDto addTicket(@RequestBody TicketInputDto ticketInputDto, @RequestHeader("Authorization") String auth) {
        UUID idToken = AuthUtils.getId(auth);
        if (!idToken.equals(ticketInputDto.getIdClient())) {
            throw new customUnprocesableException("No puedes comprar un ticket para una persona con un id diferente al que estas autenticado");
        }

        return ticketService.addTicket(ticketInputDto);
    }
}
