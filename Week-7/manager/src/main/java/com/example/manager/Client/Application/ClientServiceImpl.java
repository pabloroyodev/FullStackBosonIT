package com.example.manager.Client.Application;

import com.example.manager.Client.Domain.Client;
import com.example.manager.Client.Infrastructure.Controller.Dto.Input.ClientInputDto;
import com.example.manager.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;
import com.example.manager.Client.Infrastructure.Repository.ClientRepository;
import com.example.manager.Utils.Exceptions.customUnprocesableException;
import com.example.manager.Utils.Kafka.Producer.KafkaSender;
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
            client.setIdClient(UUID.randomUUID());
            clientRepository.save(client);

            ClientOutputDto clientDto = new ClientOutputDto(client);
            sender.sendMessage(topic, clientDto, port, "create", "client");

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
            ClientOutputDto clientOutputDto = EntityToClientOutDto(client);
            sender.sendMessage(topic, clientOutputDto, port, "update", "client");

            return clientOutputDto;
        }

        throw new customUnprocesableException("Persona con email: "+ clientInputDto.getEmail() + " ya existe.");

    }

    @Override
    public void deleteClient(UUID id) {
        ClientOutputDto clientOutputDto = EntityToClientOutDto(clientRepository.findById(id).orElseThrow());
        clientRepository.delete(clientRepository.findById(id).orElseThrow());
        sender.sendMessage(topic, clientOutputDto, port, "delete", "client");
    }

    public Client clientInputDtoToEntity(ClientInputDto clientInputDto){
        Client client = new Client();
        client.setName(clientInputDto.getName());
        client.setSurname(clientInputDto.getSurname());
        client.setEmail(clientInputDto.getEmail());
        client.setPassword(clientInputDto.getPassword());

        return client;
    }

    public Client clientOutDtoToEntity(ClientOutputDto clientOutputDto){
        Client client = new Client();
        client.setIdClient(clientOutputDto.getIdClient());
        client.setName(clientOutputDto.getName());
        client.setSurname(clientOutputDto.getSurname());
        client.setEmail(clientOutputDto.getEmail());
        client.setPassword(clientOutputDto.getPassword());

        return client;
    }

    public ClientOutputDto EntityToClientOutDto(Client client){
        ClientOutputDto clientOutputDto = new ClientOutputDto();
        clientOutputDto.setIdClient(client.getIdClient());
        clientOutputDto.setName(client.getName());
        clientOutputDto.setSurname(client.getSurname());
        clientOutputDto.setEmail(client.getEmail());
        clientOutputDto.setPassword(client.getPassword());

        return clientOutputDto;
    }

}
