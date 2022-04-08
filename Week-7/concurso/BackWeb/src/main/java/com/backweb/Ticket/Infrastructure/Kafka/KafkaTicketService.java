package com.backweb.Ticket.Infrastructure.Kafka;

import com.backweb.Client.Infrastructure.Repository.ClientRepository;
import com.backweb.Ticket.Infrastructure.Repository.TicketRepository;
import com.backweb.Trip.Infrastructure.Repository.TripRepository;
import com.backweb.Ticket.Application.TicketServiceImpl;
import com.backweb.Ticket.Domain.Ticket;
import com.backweb.Ticket.Infrastructure.Controller.Dto.Output.TicketOutputDto;
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
