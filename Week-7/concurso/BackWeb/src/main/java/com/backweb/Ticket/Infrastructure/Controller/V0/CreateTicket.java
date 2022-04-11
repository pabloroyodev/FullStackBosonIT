package com.backweb.Ticket.Infrastructure.Controller.V0;

import com.backweb.Ticket.Application.TicketService;
import com.backweb.Ticket.Infrastructure.Controller.Dto.Input.TicketInputDto;
import com.backweb.Ticket.Infrastructure.Controller.Dto.Output.TicketOutputDto;
import com.backweb.Utils.Auth.AuthUtils;
import com.backweb.Utils.Exceptions.customUnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RequestMapping("v0/ticket")
@RestController
public class CreateTicket {
    @Autowired
    TicketService ticketService;

    @Value("${urlempresa}")
    String EMPRESA;

    @PostMapping
    public TicketOutputDto addTicket(@RequestBody TicketInputDto ticketInputDto, @RequestHeader("Authorization") String auth) {
        HttpEntity<Object> request = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Void> re = new RestTemplate().exchange(EMPRESA + "/" + auth, HttpMethod.GET, request, Void.class);

        if (re.getStatusCode()== HttpStatus.OK) {
            UUID idToken = AuthUtils.getId(auth);
            if (!idToken.equals(ticketInputDto.getIdClient())) {
                throw new customUnprocesableException("No puedes comprar un ticket para una persona con un id diferente al que estas autenticado");
            }
            return ticketService.addTicket(ticketInputDto);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Autenticacion incorrecta");
    }
}
