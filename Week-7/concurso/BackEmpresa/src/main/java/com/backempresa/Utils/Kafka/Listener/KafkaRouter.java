package com.backempresa.Utils.Kafka.Listener;

import com.backempresa.Client.Infrastructure.Kafka.KafkaClientService;
import com.backempresa.Mail.Domain.Mail;
import com.backempresa.Mail.Infrastructure.Kafka.KafkaMailService;
import com.backempresa.Ticket.Infrastructure.Kafka.KafkaTicketService;
import com.backempresa.Trip.Infrastructure.Kafka.KafkaTripService;
import com.backempresa.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;
import com.backempresa.Ticket.Infrastructure.Controller.Dto.Output.TicketOutputDto;
import com.backempresa.Trip.Infrastructure.Controller.Dto.Output.TripOutputDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
            log.info("Mensaje recibido");
            switch (clase[0]) {
                case "client" -> {
                    log.info("RECIBIDO CLIENTE! accion: " + action[0]);
                    kafkaClientService.listenTopic(action[0], mapper.readValue((String)record.value(), ClientOutputDto.class));
                }
                case "ticket" -> {
                    log.info("RECIBIDO TICKET! accion:" + action[0]);
                    kafkaTicketService.listenTopic(action[0], mapper.readValue((String)record.value(), TicketOutputDto.class));
                }
                case "trip" -> {
                    log.info("RECIBIDO TRIP! accion: " + action[0]);
                    kafkaTripService.listenTopic(action[0], mapper.readValue((String)record.value(), TripOutputDto.class));
                }
                case "mail" -> {
                    log.info("RECIBIDO MAIL! accion: " + action[0]);
                    kafkaMailService.listenTopic(action[0], mapper.readValue((String)record.value(), Mail.class));
                }
                default -> log.info("error");
            }
        }
    }
}
