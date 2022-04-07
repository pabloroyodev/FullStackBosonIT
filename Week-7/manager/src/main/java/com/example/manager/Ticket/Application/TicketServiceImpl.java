package com.example.manager.Ticket.Application;

import com.example.manager.Client.Domain.Client;
import com.example.manager.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;
import com.example.manager.Client.Infrastructure.Repository.ClientRepository;
import com.example.manager.Ticket.Domain.Ticket;
import com.example.manager.Ticket.Infrastructure.Controller.Dto.Input.TicketInputDto;
import com.example.manager.Ticket.Infrastructure.Controller.Dto.Output.TicketOutputDto;
import com.example.manager.Ticket.Infrastructure.Repository.TicketRepository;
import com.example.manager.Trip.Domain.Trip;
import com.example.manager.Trip.Infrastructure.Repository.TripRepository;
import com.example.manager.Utils.Exceptions.customUnprocesableException;
import com.example.manager.Utils.Kafka.Producer.KafkaSender;
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
        for (int i = 0; i < client.getTickets().size(); i++) {
            if (client.getTickets().get(i).getTrip().getIdTrip().equals(ticketInputDto.getIdTrip())) {
                throw new customUnprocesableException("Cliente ya tiene ticket para el trayecto: " + ticketInputDto.getIdTrip() + " solo 1 por persona.");
            }
        }

        Ticket ticket = ticketInputDtoToEntity(ticketInputDto);
        ticket.setIdTicket(UUID.randomUUID());
        Trip trip = tripRepository.findById(ticketInputDto.getIdTrip()).stream().findFirst().orElseThrow();

        if (trip.getSeats() >= 1) {
            trip.setDecreaseSeats(1);
            ticketRepository.save(ticket);

            TicketOutputDto ticketOutputDto = new TicketOutputDto(ticket);
            sender.sendMessage(topic, ticketOutputDto, port, "create", "ticket");

            return ticketOutputDto;
        }

        trip.setIncreaseDeniedSeats(1);
        throw new customUnprocesableException("No quedan asientos para este trayecto: ");
    }

    @Override
    public void deleteTicket(UUID id) {
        ticketRepository.delete(ticketRepository.findById(id).orElseThrow());
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
}
