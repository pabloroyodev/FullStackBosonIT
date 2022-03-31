package com.example.manager.Trip.Domain;

import com.example.manager.Ticket.Domain.Ticket;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity @Data
public class Trip {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "id_trip")
    private Integer idTrip;

    private Date date;

    private String departure;

    private String arrival;

    private Integer seats = 40;

    private boolean issue = false;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;
}
