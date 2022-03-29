package com.example.ex28.topicReceiver;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerController {

    @KafkaListener(topics = "topicName", groupId = "group_id")
    public void consumer(String message) {
        System.out.println("Mensaje recibido!: " + message );
    }
}
