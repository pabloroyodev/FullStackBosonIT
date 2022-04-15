package com.backempresa.Client.Infrastructure.Kafka;

import com.backempresa.Client.Application.ClientServiceImpl;
import lombok.extern.slf4j.Slf4j;
import com.backempresa.Client.Domain.Client;
import com.backempresa.Client.Infrastructure.Repository.ClientRepository;
import com.backempresa.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaClientService {
    @Autowired
    ClientServiceImpl clientService;

    @Autowired
    ClientRepository clientRepository;

    public void listenTopic(String action, ClientOutputDto clientOutputDto) {
        switch (action) {
            case "create" -> {
                Client client = clientService.clientOutDtoToEntity(clientOutputDto);
                clientRepository.save(client);

                log.info("CREATE SUCCESS");
            }

            case "update" -> {
                Client client = clientRepository.findById(clientOutputDto.getIdClient()).orElseThrow();

                client.setName(clientOutputDto.getName());
                client.setSurname(clientOutputDto.getSurname());
                client.setEmail(clientOutputDto.getEmail());
                client.setPassword(clientOutputDto.getPassword());

                clientRepository.save(client);

                log.info("UPDATE SUCCESS");
            }

            case "delete" -> {
                clientRepository.delete(clientRepository.findById(clientOutputDto.getIdClient()).orElseThrow());
                log.info("DELETE SUCCESS");
            }

            default -> {
                log.info("ERROR KAFKA SERVICE CLIENT! ACCION NO ESPECIFICADA (create, update o delete)");
            }
        }
    }
}
