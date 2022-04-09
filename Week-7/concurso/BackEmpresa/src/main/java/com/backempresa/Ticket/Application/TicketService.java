package com.backempresa.Ticket.Application;

import com.backempresa.Ticket.Infrastructure.Controller.Dto.Input.TicketInputDto;
import com.backempresa.Ticket.Infrastructure.Controller.Dto.Output.TicketOutputDto;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    List<TicketOutputDto> getAllTickets();
    TicketOutputDto filterTicketById(UUID id);
    TicketOutputDto addTicket(TicketInputDto ticketInputDto);
    void deleteTicket(UUID id);
}
