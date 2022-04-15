package com.backempresa.Ticket.Infrastructure.Controller.Dto.Input;

import lombok.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data @NoArgsConstructor
public class TicketInputDto implements Serializable {

    private String details;

    @NonNull
    private UUID idClient;

    @NonNull
    private UUID idTrip;
}
