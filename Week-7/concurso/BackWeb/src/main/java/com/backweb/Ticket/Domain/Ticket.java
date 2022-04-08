package com.backweb.Ticket.Domain;

import com.backweb.Client.Domain.Client;
import com.backweb.Trip.Domain.Trip;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity @Data
public class Ticket {
    @Id @Column(name = "id_ticket")
    private UUID idTicket;

    private String details;

    @ManyToOne @JoinColumn(name = "fk_client") @EqualsAndHashCode.Exclude @ToString.Exclude
    private Client client;

    @ManyToOne @JoinColumn(name = "fk_trip") @EqualsAndHashCode.Exclude @ToString.Exclude
    private Trip trip;
}
