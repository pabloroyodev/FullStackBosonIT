package com.backempresa.Ticket.Infrastructure.Kafka;

import com.backempresa.Client.Infrastructure.Repository.ClientRepository;
import com.backempresa.Ticket.Infrastructure.Repository.TicketRepository;
import com.backempresa.Trip.Domain.Trip;
import com.backempresa.Trip.Infrastructure.Repository.TripRepository;
import com.backempresa.Ticket.Application.TicketServiceImpl;
import com.backempresa.Ticket.Domain.Ticket;
import com.backempresa.Ticket.Infrastructure.Controller.Dto.Output.TicketOutputDto;
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
                Trip trip = tripRepository.findById(ticketOutputDto.getIdTrip()).stream().findFirst().orElseThrow();
                trip.setDecreaseSeats(1);
                ticketRepository.save(ticket);
                tripRepository.save(trip);
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
                Trip trip = tripRepository.findById(ticketOutputDto.getIdTrip()).stream().findFirst().orElseThrow();
                trip.setIncreaseSeats(1);
                tripRepository.save(trip);
                System.out.println("DELETE SUCCESS");
            }

            case "denied" -> {
                Trip trip = tripRepository.findById(ticketOutputDto.getIdTrip()).stream().findFirst().orElseThrow();
                trip.setIncreaseDeniedSeats(1);
                tripRepository.save(trip);
                System.out.println("DENIED COUNTER INCREASED SUCCESSFULLY");
            }
        }
    }
}
