package com.example.manager.Client.Domain;

import com.example.manager.Ticket.Domain.Ticket;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity @Data
public class Client {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "id_client")
    private Integer idClient;

    private String name;

    private String surname;

    private String email;

    private String password;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;
}
