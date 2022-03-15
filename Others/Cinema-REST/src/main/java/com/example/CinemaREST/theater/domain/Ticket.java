package com.example.CinemaREST.theater.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Ticket {
    UUID token;
    Seat ticket;

    public Ticket(UUID token, Seat ticket) {
        this.token = token;
        this.ticket = ticket;
    }
}
