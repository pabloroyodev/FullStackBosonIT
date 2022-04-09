package com.backempresa.Client.Infrastructure.Kafka;

import com.backempresa.Client.Application.ClientServiceImpl;
import com.backempresa.Client.Domain.Client;
import com.backempresa.Client.Infrastructure.Repository.ClientRepository;
import com.backempresa.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaClientService {
    @Autowired
    ClientServiceImpl clientService;

    @Autowired
    ClientRepository clientRepository;

    public void listenTopic(String action, ClientOutputDto clientOutputDto) {
        switch (action) {
            case "create" -> {
                Client client = clientService.clientOutDtoToEntity(clientOutputDto);
                System.out.println(client);
                clientRepository.save(client);
                System.out.println("CREATE SUCCESS");
            }

            case "update" -> {
                Client client = clientRepository.findById(clientOutputDto.getIdClient()).orElseThrow();

                client.setName(clientOutputDto.getName());
                client.setSurname(clientOutputDto.getSurname());
                client.setEmail(clientOutputDto.getEmail());
                client.setPassword(clientOutputDto.getPassword());

                clientRepository.save(client);

                System.out.println("UPDATE SUCCESS");
            }

            case "delete" -> {
                clientRepository.delete(clientRepository.findById(clientOutputDto.getIdClient()).orElseThrow());
                System.out.println("DELETE SUCCESS");
            }
        }
    }
}
