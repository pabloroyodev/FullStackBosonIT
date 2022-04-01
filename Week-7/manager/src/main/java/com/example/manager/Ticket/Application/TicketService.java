package com.example.manager.Ticket.Application;

import com.example.manager.Ticket.Infrastructure.Controller.Dto.Input.TicketInputDto;
import com.example.manager.Ticket.Infrastructure.Controller.Dto.Output.TicketOutputDto;

import java.util.List;

public interface TicketService {
    List<TicketOutputDto> getAllTickets();
    TicketOutputDto filterTicketById(Integer id);
    TicketOutputDto addTicket(TicketInputDto ticketInputDto) throws Exception;
    void deleteTicket(Integer id);
}
