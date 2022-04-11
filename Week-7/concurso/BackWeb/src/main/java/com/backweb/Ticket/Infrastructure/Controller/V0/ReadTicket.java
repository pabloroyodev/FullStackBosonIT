package com.backweb.Ticket.Infrastructure.Controller.V0;

import com.backweb.Ticket.Application.TicketService;
import com.backweb.Ticket.Infrastructure.Controller.Dto.Output.TicketOutputDto;
import com.backweb.Ticket.Infrastructure.Repository.TicketRepository;
import com.backweb.Utils.Auth.AuthUtils;
import com.backweb.Utils.Exceptions.customUnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RequestMapping("v0/ticket")
@RestController
public class ReadTicket {
    @Autowired
    TicketService ticketService;

    @Autowired
    TicketRepository ticketRepository;

    @Value("${urlempresa}")
    String EMPRESA;

    /*
    @GetMapping
    public List<TicketOutputDto> findAll(){
        return ticketService.getAllTickets();
    }
    */

    @GetMapping("{id}")
    public TicketOutputDto filterTicketById(@PathVariable UUID id, @RequestHeader("Authorization") String auth){
        HttpEntity<Object> request = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Void> re = new RestTemplate().exchange(EMPRESA + "/" + auth, HttpMethod.GET, request, Void.class);

        if (re.getStatusCode()== HttpStatus.OK) {
            UUID idToken = AuthUtils.getId(auth);
            if (!idToken.equals(ticketRepository.findById(id).orElseThrow().getClient().getIdClient())) {
                throw new customUnprocesableException("La persona autenticada no corresponde con al persona de la que quieres leer el ticket");
            }
            return ticketService.filterTicketById(id);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Autenticacion incorrecta");
    }
}
