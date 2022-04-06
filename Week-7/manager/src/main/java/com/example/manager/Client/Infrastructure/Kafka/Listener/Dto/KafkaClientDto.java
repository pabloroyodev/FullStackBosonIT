package com.example.manager.Client.Infrastructure.Kafka.Listener.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data @NoArgsConstructor @AllArgsConstructor
public class KafkaClientDto implements Serializable {

    private int microservicePort;

    private String domain;

    private String action;

    private UUID idClient;

    private String name;

    private String surname;

    private String email;

    private String password;
}
