package com.backempresa.Ticket.Infrastructure.Kafka;

import com.backempresa.Client.Infrastructure.Repository.ClientRepository;
import com.backempresa.Ticket.Infrastructure.Repository.TicketRepository;
import com.backempresa.Trip.Domain.Trip;
import com.backempresa.Trip.Infrastructure.Repository.TripRepository;
import com.backempresa.Ticket.Application.TicketServiceImpl;
import com.backempresa.Ticket.Domain.Ticket;
import com.backempresa.Ticket.Infrastructure.Controller.Dto.Output.TicketOutputDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaTicketService {

    @Autowired
    TicketServiceImpl ticketService;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TripRepository tripRepository;

    public void listenTopic(String action, TicketOutputDto ticketOutputDto) {
        switch (action) {
            case "create" -> {
                Ticket ticket = ticketService.ticketOutDtoToEntity(ticketOutputDto);

                Trip trip = tripRepository.findById(ticketOutputDto.getIdTrip()).stream().findFirst().orElseThrow();
                trip.setDecreaseSeats(1);
                ticketRepository.save(ticket);
                tripRepository.save(trip);

                log.info("CREATE SUCCESS");
            }

            case "update" -> {
                log.info("TICKETS CAN´T BE UPDATED, ASK FOR REFUND AND BUY ANOTHER");
            }

            case "delete" -> {
                ticketRepository.delete(ticketRepository.findById(ticketOutputDto.getIdTicket()).orElseThrow());
                Trip trip = tripRepository.findById(ticketOutputDto.getIdTrip()).stream().findFirst().orElseThrow();
                trip.setIncreaseSeats(1);
                tripRepository.save(trip);

                log.info("DELETE SUCCESS");
            }

            case "denied" -> {
                Trip trip = tripRepository.findById(ticketOutputDto.getIdTrip()).stream().findFirst().orElseThrow();
                trip.setIncreaseDeniedSeats(1);
                tripRepository.save(trip);

                log.info("DENIED COUNTER INCREASED SUCCESSFULLY");
            }

            default -> {
                log.info("ERROR KAFKA SERVICE TICKET! ACCION NO ESPECIFICADA (create, update, denied o delete)");
            }
        }
    }
}
