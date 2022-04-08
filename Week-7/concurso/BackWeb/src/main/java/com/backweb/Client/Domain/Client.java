package com.backweb.Client.Domain;

import com.backweb.Ticket.Domain.Ticket;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity @Data
public class Client {
    @Id @Column(name = "id_client")
    private UUID idClient;

    private String name;

    private String surname;

    private String email;

    private String password;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;
}