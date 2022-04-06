package com.example.manager.Utils.Kafka;

import com.example.manager.Client.Infrastructure.Kafka.Listener.Dto.KafkaClientDto;
import org.springframework.kafka.annotation.KafkaListener;

public class TopicRouter {

  @KafkaListener(topics = "${topic}", groupId = "${group}")
  public void listenTopic(KafkaClientDto clientDto) {
        System.out.println(clientDto.toString());
        //Pedido pedido1 = new Pedido(pedido.getProducto(), pedido.getUnidades(), pedido.getPrecio());
    }
}
