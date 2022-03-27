package com.example.ex28.topicSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaSenderController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private final String KAFKATOPIC = "topicname";

    @PostMapping("/add")
    public String sendMessage (@RequestBody String body) {
        try {
            kafkaTemplate.send(KAFKATOPIC, body);
            return "El mensaje se envio correctamente";
        } catch (Exception e) {
            return "Error! El servidor kafka no esta disponible (Lo has encendido?)";
        }
    }
}
