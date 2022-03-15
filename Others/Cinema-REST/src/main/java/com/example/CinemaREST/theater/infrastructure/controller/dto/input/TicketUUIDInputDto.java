package com.example.CinemaREST.theater.infrastructure.controller.dto.input;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class TicketUUIDInputDto implements Serializable {
    private UUID token;
}
