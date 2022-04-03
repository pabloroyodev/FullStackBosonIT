package com.example.manager.Ticket.Domain;

import com.example.manager.Client.Domain.Client;
import com.example.manager.Trip.Domain.Trip;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity @Data
public class Ticket {
    @Id @GeneratedValue @Column(name = "id_ticket")
    private UUID idTicket;

    private String details;

    @ManyToOne @JoinColumn(name = "fk_client")
    private Client client;

    @ManyToOne @JoinColumn(name = "fk_trip")
    private Trip trip;
}
