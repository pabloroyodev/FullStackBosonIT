package com.example.manager.Utils.Kafka.Listener;

import com.example.manager.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;
import com.example.manager.Client.Infrastructure.Kafka.KafkaClientService;
import com.example.manager.Ticket.Infrastructure.Controller.Dto.Output.TicketOutputDto;
import com.example.manager.Ticket.Infrastructure.Kafka.KafkaTicketService;
import com.example.manager.Trip.Infrastructure.Controller.Dto.Output.TripOutputDto;
import com.example.manager.Trip.Infrastructure.Kafka.KafkaTripService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaRouter {
    @Value("${server.port}")
    String puerto;

    @Autowired
    KafkaClientService kafkaClientService;

    @Autowired
    KafkaTripService kafkaTripService;

    @Autowired
    KafkaTicketService kafkaTicketService;

    @KafkaListener(topics = "${topic}", groupId = "${group}")
    public void listenTopic(@Payload ConsumerRecord<String, Object> record) throws JsonProcessingException {

        final String[] port = new String[1];
        final String[] action = new String[1];
        final String[] clase = new String[1];
        ObjectMapper mapper = new ObjectMapper();

        record.headers().headers("port").forEach(header -> {
            port[0] = new String(header.value());
        });

        record.headers().headers("action").forEach(header -> {
            action[0] = new String(header.value());
        });

        record.headers().headers("clase").forEach(header -> {
            clase[0] = new String(header.value());
        });

        if (!port[0].equals(puerto)) {
            System.out.println("Mensaje recibido");
            switch (clase[0]) {
                case "client" -> {
                    System.out.println("RECIBIDO CLIENTE! accion: " + action[0]);
                    kafkaClientService.listenTopic(action[0], mapper.readValue((String)record.value(), ClientOutputDto.class));
                }
                case "ticket" -> {
                    System.out.println("RECIBIDO TICKET! accion:" + action[0]);
                    kafkaTicketService.listenTopic(action[0], mapper.readValue((String)record.value(), TicketOutputDto.class));
                }
                case "trip" -> {
                    System.out.println("RECIBIDO TRIP! accion: " + action[0]);
                    kafkaTripService.listenTopic(action[0], mapper.readValue((String)record.value(), TripOutputDto.class));
                }

                default -> System.out.println("error");
            }
        }
    }
}
