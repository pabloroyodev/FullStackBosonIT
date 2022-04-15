package com.backempresa.Utils.Kafka.Producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaSender {
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String topico, Object obj, String port, String accion, String clase) {
        ObjectMapper mapper = new ObjectMapper();

        String jsonObject = null;
        try {jsonObject = mapper.writeValueAsString(obj);}
        catch (JsonProcessingException ignored) {
            log.info("EXCEPCION EN PROCESAR EL JSON");
        }

        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topico, jsonObject);
        producerRecord.headers().add("port", port.getBytes());
        producerRecord.headers().add("action", accion.getBytes());
        producerRecord.headers().add("clase", clase.getBytes());

        log.info("Mensaje enviado!");
        log.info("El mensaje es: " + producerRecord);
        kafkaTemplate.send(producerRecord);
    }
}
