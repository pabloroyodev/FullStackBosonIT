package com.backempresa.Ticket.Infrastructure.Controller.Dto.Input;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data @NoArgsConstructor
public class TicketInputDto implements Serializable {

    private String details;

    @NotNull
    private UUID idClient;

    @NotNull
    private UUID idTrip;
}
