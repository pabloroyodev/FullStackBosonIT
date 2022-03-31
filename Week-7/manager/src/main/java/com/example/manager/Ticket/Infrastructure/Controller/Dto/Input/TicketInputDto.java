package com.example.manager.Ticket.Infrastructure.Controller.Dto.Input;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @NoArgsConstructor
public class TicketInputDto implements Serializable {

    private String details;

    @NotNull
    private Integer idClient;

    @NotNull
    private Integer idTrip;
}
