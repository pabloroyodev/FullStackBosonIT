package com.example.manager.Ticket.Infrastructure.Kafka;

import com.example.manager.Client.Application.ClientServiceImpl;
import com.example.manager.Client.Domain.Client;
import com.example.manager.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;
import com.example.manager.Client.Infrastructure.Repository.ClientRepository;
import com.example.manager.Ticket.Application.TicketService;
import com.example.manager.Ticket.Application.TicketServiceImpl;
import com.example.manager.Ticket.Domain.Ticket;
import com.example.manager.Ticket.Infrastructure.Controller.Dto.Output.TicketOutputDto;
import com.example.manager.Ticket.Infrastructure.Repository.TicketRepository;
import com.example.manager.Trip.Infrastructure.Repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaTicketService {
    @Autowired
    TicketServiceImpl ticketService;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TripRepository tripRepository;

    public void listenTopic(String action, TicketOutputDto ticketOutputDto) {
        switch (action) {
            case "create" -> {
                Ticket ticket = ticketService.ticketOutDtoToEntity(ticketOutputDto);
                System.out.println(ticket);
                ticketRepository.save(ticket);
                System.out.println("CREATE SUCCESS");
            }

            case "update" -> {
                /*Ticket ticket = ticketRepository.findById(ticketOutputDto.getIdTicket()).orElseThrow();

                ticket.setDetails(ticketOutputDto.getDetails());
                ticket.setClient(clientRepository.findById(ticketOutputDto.getIdClient()).orElseThrow());
                ticket.setTrip(tripRepository.findById(ticketOutputDto.getIdTrip()).orElseThrow());

                ticketRepository.save(ticket);*/

                System.out.println("TICKETS CAN´T BE UPDATED, ASK FOR REFUND AND BUY ANOTHER");
            }

            case "delete" -> {
                ticketRepository.delete(ticketRepository.findById(ticketOutputDto.getIdTicket()).orElseThrow());
                System.out.println("DELETE SUCCESS");
            }
        }
    }
}
