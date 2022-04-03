package com.example.manager.Trip.Domain;

import com.example.manager.Ticket.Domain.Ticket;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity @Data
public class Trip {
    @Id @GeneratedValue @Column(name = "id_trip")
    private UUID idTrip;

    private Date date;

    private String departure;

    private String arrival;

    private Integer seats = 40;

    @Column(name = "denied_seats")
    private Integer deniedSeats = 0;

    private boolean issue;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;
}
