package com.backweb.Ticket.Application;

import com.backweb.Client.Domain.Client;
import com.backweb.Client.Infrastructure.Repository.ClientRepository;
import com.backweb.Ticket.Infrastructure.Repository.TicketRepository;
import com.backweb.Trip.Domain.Trip;
import com.backweb.Trip.Infrastructure.Controller.Dto.Output.TripOutputDto;
import com.backweb.Trip.Infrastructure.Repository.TripRepository;
import com.backweb.Utils.Exceptions.customUnprocesableException;
import com.backweb.Utils.Kafka.Producer.KafkaSender;
import com.backweb.Ticket.Domain.Ticket;
import com.backweb.Ticket.Infrastructure.Controller.Dto.Input.TicketInputDto;
import com.backweb.Ticket.Infrastructure.Controller.Dto.Output.TicketOutputDto;
import com.backweb.Utils.Mail.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    KafkaSender sender;

    @Autowired
    MailSenderService mailSenderService;

    @Value("${server.port}")
    String port;

    @Value("${topic}")
    String topic;

    @Override
    public List<TicketOutputDto> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(TicketOutputDto::new).collect(Collectors.toList());
    }

    @Override
    public TicketOutputDto filterTicketById(UUID id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        return new TicketOutputDto(ticket);
    }

    @Override
    public TicketOutputDto addTicket(TicketInputDto ticketInputDto) {
        Client client = clientRepository.findById(ticketInputDto.getIdClient()).orElseThrow();
        Trip trip = tripRepository.findById(ticketInputDto.getIdTrip()).stream().findFirst().orElseThrow();

        if (trip.isIssue()) {
            Ticket ticket = ticketInputDtoToEntity(ticketInputDto);
            trip.setIncreaseDeniedSeats(1);
            tripRepository.save(trip);

            TicketOutputDto ticketOutputDto = new TicketOutputDto(ticket);
            sender.sendMessage(topic, ticketOutputDto, port, "denied", "ticket");

            throw new customUnprocesableException("Viaje cancelado. (Mantenimiento, huelga, etc.)");
        }

        for (int i = 0; i < client.getTickets().size(); i++) {
            if (client.getTickets().get(i).getTrip().getIdTrip().equals(ticketInputDto.getIdTrip())) {
                throw new customUnprocesableException("Cliente ya tiene ticket para el trayecto: " + ticketInputDto.getIdTrip() + " solo 1 por persona.");
            }
        }

        Ticket ticket = ticketInputDtoToEntity(ticketInputDto);
        ticket.setIdTicket(UUID.randomUUID());


        if (trip.getSeats() >= 1) {
            trip.setDecreaseSeats(1);
            ticketRepository.save(ticket);

            TicketOutputDto ticketOutputDto = new TicketOutputDto(ticket);
            sender.sendMessage(topic, ticketOutputDto, port, "create", "ticket");

            mailSenderService.sendMail(
                  ticket.getClient().getEmail(),
                  "Ticket Comprado",
                  "Le informamos que ha adquirido un ticket para el viaje: "
                      + trip.getDeparture() + " a " + trip.getArrival() + ".\n"
                      + "Su identificador de viaje es: " + ticket.getIdTicket());
            return ticketOutputDto;
        }

        trip.setIncreaseDeniedSeats(1);
        tripRepository.save(trip);
        TicketOutputDto ticketOutputDto = new TicketOutputDto(ticket);
        sender.sendMessage(topic, ticketOutputDto, port, "denied", "ticket");

        throw new customUnprocesableException("No quedan asientos para este trayecto");
    }

    @Override
    public void deleteTicket(UUID id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        Trip trip = tripRepository.findById(ticketRepository.findById(id).orElseThrow().getTrip().getIdTrip()).orElseThrow();
        ticketRepository.delete(ticketRepository.findById(id).orElseThrow());
        trip.setIncreaseSeats(1);
        tripRepository.save(trip);

        TicketOutputDto ticketOutputDto = EntityToTicketOutDto(ticket);
        sender.sendMessage(topic, ticketOutputDto, port, "delete", "ticket");

        mailSenderService.sendMail(
                ticket.getClient().getEmail(),
                "Ticket Cancelado",
                "Le informamos que se ha cancelado el ticket para el viaje: "
                        + trip.getDeparture() + " a " + trip.getArrival() + ".");
    }

    public Ticket ticketInputDtoToEntity(TicketInputDto ticketInputDto) {
        Ticket ticket = new Ticket();
        ticket.setDetails(ticketInputDto.getDetails());
        ticket.setClient(clientRepository.findById(ticketInputDto.getIdClient()).orElseThrow());
        ticket.setTrip(tripRepository.findById(ticketInputDto.getIdTrip()).orElseThrow());

        return ticket;
    }

    public Ticket ticketOutDtoToEntity(TicketOutputDto ticketOutputDto){
        Ticket ticket = new Ticket();
        ticket.setIdTicket(ticketOutputDto.getIdTicket());
        ticket.setDetails(ticketOutputDto.getDetails());
        ticket.setClient(clientRepository.findById(ticketOutputDto.getIdClient()).orElseThrow());
        ticket.setTrip(tripRepository.findById(ticketOutputDto.getIdTrip()).orElseThrow());

        return ticket;
    }

    public TicketOutputDto EntityToTicketOutDto(Ticket ticket) {
        TicketOutputDto ticketOutputDto = new TicketOutputDto();

        ticketOutputDto.setIdTicket(ticket.getIdTicket());
        ticketOutputDto.setDetails(ticket.getDetails());
        ticketOutputDto.setIdClient(ticket.getClient().getIdClient());
        ticketOutputDto.setIdTrip(ticket.getTrip().getIdTrip());

        return ticketOutputDto;
    }
}
