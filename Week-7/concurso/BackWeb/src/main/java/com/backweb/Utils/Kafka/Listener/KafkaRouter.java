package com.backweb.Utils.Kafka.Listener;

import com.backweb.Client.Infrastructure.Kafka.KafkaClientService;
import com.backweb.Mail.Domain.Mail;
import com.backweb.Mail.Infrastructure.Kafka.KafkaMailService;
import com.backweb.Ticket.Infrastructure.Kafka.KafkaTicketService;
import com.backweb.Trip.Infrastructure.Kafka.KafkaTripService;
import com.backweb.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;
import com.backweb.Ticket.Infrastructure.Controller.Dto.Output.TicketOutputDto;
import com.backweb.Trip.Infrastructure.Controller.Dto.Output.TripOutputDto;
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

    @Autowired
    KafkaMailService kafkaMailService;

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
                case "mail" -> {
                    System.out.println("RECIBIDO MAIL! accion: " + action[0]);
                    kafkaMailService.listenTopic(action[0], mapper.readValue((String)record.value(), Mail.class));
                }
                default -> System.out.println("error");
            }
        }
    }
}
