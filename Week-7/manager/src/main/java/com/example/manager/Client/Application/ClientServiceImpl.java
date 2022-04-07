package com.example.manager.Client.Application;

import com.example.manager.Client.Domain.Client;
import com.example.manager.Client.Infrastructure.Controller.Dto.Input.ClientInputDto;
import com.example.manager.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;
import com.example.manager.Client.Infrastructure.Repository.ClientRepository;
import com.example.manager.Utils.Exceptions.customUnprocesableException;
import com.example.manager.Utils.Kafka.Producer.KafkaSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    KafkaSender sender;

    @Value("${server.port}")
    String port;

    @Value("${topic}")
    String topic;

    @Override
    public List<ClientOutputDto> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(ClientOutputDto::new).collect(Collectors.toList());
    }

    @Override
    public ClientOutputDto filterClientById(UUID id) {
        Client client = clientRepository.findById(id).orElseThrow();
        return new ClientOutputDto(client);
    }

    @Override
    public ClientOutputDto filterClientByEmail(String email) {
        Client client = clientRepository.findByEmail(email);
        return new ClientOutputDto(client);
    }

    @Override
    public ClientOutputDto addClient(ClientInputDto clientInputDto) {
        if (clientRepository.findByEmail(clientInputDto.getEmail()) == null) {
            Client client = clientInputDtoToEntity(clientInputDto);
            clientRepository.save(client);

            ClientOutputDto clientDto = new ClientOutputDto(client);
            sender.sendMessage(topic, clientDto, port, "delete", "client");

            return clientDto;
        }

        throw new customUnprocesableException("Persona con email: "+ clientInputDto.getEmail() + " ya existe.");
    }

    @Override
    public ClientOutputDto updateClient(UUID id, ClientInputDto clientInputDto) {

        if (clientRepository.findByEmail(clientInputDto.getEmail()) == null) {
            Client client = clientRepository.findById(id).orElseThrow();

            client.setName(clientInputDto.getName());
            client.setSurname(clientInputDto.getSurname());
            client.setEmail(clientInputDto.getEmail());
            client.setPassword(clientInputDto.getPassword());

            clientRepository.save(client);
            return new ClientOutputDto(client);
        }

        throw new customUnprocesableException("Persona con email: "+ clientInputDto.getEmail() + " ya existe.");

    }

    @Override
    public void deleteClient(UUID id) {
        clientRepository.delete(clientRepository.findById(id).orElseThrow());
    }

    public Client clientInputDtoToEntity(ClientInputDto clientInputDto){
        Client client = new Client();
        client.setName(clientInputDto.getName());
        client.setSurname(clientInputDto.getSurname());
        client.setEmail(clientInputDto.getEmail());
        client.setPassword(clientInputDto.getPassword());

        return client;
    }
}
