package com.example.CinemaREST.theater.infrastructure.controller;

import com.example.CinemaREST.theater.domain.Seat;
import com.example.CinemaREST.theater.domain.Theater;
import com.example.CinemaREST.theater.domain.Ticket;
import com.example.CinemaREST.theater.infrastructure.controller.dto.input.TicketInputDto;
import com.example.CinemaREST.theater.infrastructure.controller.dto.input.TicketUUIDInputDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@RestController
public class TheaterController {
    private Theater theater = new Theater(9, 9);
    ArrayList<Ticket> boughtTickets = new ArrayList<Ticket>();  //Esta propiedad almacena los tickets comprados.

    @GetMapping("/seats")
    public Theater getRoom() {
        return theater;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody TicketInputDto data) {
        int row = data.getRow();
        int column = data.getColumn();

        List<Seat> availableSeats = theater.getSeats();

        if(row > theater.getRows() || row < 1 || column > theater.getCols() || column < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ConcurrentHashMap<>(Map.of("error", "The number of a row or a column is out of bounds!")));
        }

        Iterator<Seat> i = availableSeats.iterator();
        while (i.hasNext()) {
            Seat seat = i.next();
            if (seat.getRow() == row && seat.getColumn() == column) {
                UUID uuid=UUID.randomUUID();
                Ticket ticket = new Ticket(uuid, seat);
                i.remove();
                boughtTickets.add(ticket);
                return ResponseEntity.status(HttpStatus.OK).body(ticket);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ConcurrentHashMap<>(Map.of("error", "The ticket has been already purchased!")));
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody TicketUUIDInputDto uuid) {
        for (int j = 0; j < boughtTickets.size(); j++) {
            if (uuid.getToken().equals(boughtTickets.get(j).getToken())) {
                Seat transfer = boughtTickets.get(j).getTicket();
                boughtTickets.remove(j);
                List<Seat> availableSeats = theater.getSeats();
                availableSeats.add(new Seat(transfer.getRow(), transfer.getColumn()));
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ConcurrentHashMap<>(Map.of("returned_ticket", transfer)));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ConcurrentHashMap<>(Map.of("error", "Wrong token!")));
    }
}
