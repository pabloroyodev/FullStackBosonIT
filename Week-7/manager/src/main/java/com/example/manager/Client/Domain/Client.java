package com.example.manager.Client.Domain;

import com.example.manager.Ticket.Domain.Ticket;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity @Data
public class Client {
    @Id @GeneratedValue @Column(name = "id_client")
    private UUID idClient;

    private String name;

    private String surname;

    private String email;

    private String password;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;
}
