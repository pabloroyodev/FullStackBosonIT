package com.example.manager.Client.Infrastructure.Controller.Dto.Output;

import com.example.manager.Client.Domain.Client;
import com.example.manager.Ticket.Domain.Ticket;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data @NoArgsConstructor
public class ClientOutputDto implements Serializable {
    private UUID idClient;

    private String name;

    private String surname;

    private String email;

    private String password;

    private List<UUID> tickets;

    public ClientOutputDto(Client client){
        setIdClient(client.getIdClient());
        setName(client.getName());
        setSurname(client.getSurname());
        setEmail(client.getEmail());
        setPassword(client.getPassword());

        List<UUID> tickets = new ArrayList<>();
        if(client.getTickets() != null && client.getTickets().size() != 0) {
            for(int i = 0; i < client.getTickets().size(); i++){
                tickets.add(client.getTickets().get(i).getIdTicket());
            }
        }
        setTickets(tickets);
    }
}
