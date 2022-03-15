package com.example.CinemaREST.theater.infrastructure.controller.dto.input;

import lombok.Data;

import java.io.Serializable;

@Data
public class TicketInputDto implements Serializable {
    private int row;
    private int column;
}
