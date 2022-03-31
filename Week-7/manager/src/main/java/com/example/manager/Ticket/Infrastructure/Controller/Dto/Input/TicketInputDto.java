package com.example.manager.Ticket.Infrastructure.Controller.Dto.Input;

import com.example.manager.Client.Domain.Client;
import com.example.manager.Trip.Domain.Trip;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @NoArgsConstructor
public class TicketInputDto implements Serializable {

    private String details;

    @NotNull
    private Client client;

    @NotNull
    private Trip trip;
}
