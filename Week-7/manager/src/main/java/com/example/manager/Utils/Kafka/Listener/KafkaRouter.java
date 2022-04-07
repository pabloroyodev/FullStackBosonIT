package com.example.manager.Utils.Kafka.Listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaRouter {

    @KafkaListener(topics = "${topic}", groupId = "${group}")
    public void listenTopic(@Payload ConsumerRecord<String, Object> record) {
        System.out.println("Mensaje recibido");

        final String[] port = new String[1];
        final String[] action = new String[1];
        final String[] clase = new String[1];
        //ObjectMapper mapper = new ObjectMapper();

        record.headers().headers("port").forEach(header -> {
            port[0] = new String(header.value());
            System.out.println(port[0]);
        });

        record.headers().headers("action").forEach(header -> {
            action[0] = new String(header.value());
            System.out.println(action[0]);
        });

        record.headers().headers("clase").forEach(header -> {
            clase[0] = new String(header.value());
            System.out.println(clase[0]);
        });

    /*if (!port[0].equals("${server.port}")) {
        switch (clase[0]) {
            case "client" -> System.out.println("CLIENTE DETECTADO: " + action[0]);
            case "ticket" -> System.out.println("TICKET DETECTADO: " + action[0]);
        }
    }*/

    System.out.println(port[0]);
    System.out.println(clase[0]);
    System.out.println(action[0]);

    }
}
