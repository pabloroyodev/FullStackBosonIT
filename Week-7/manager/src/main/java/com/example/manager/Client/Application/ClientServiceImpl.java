package com.example.manager.Client.Application;

import com.example.manager.Client.Domain.Client;
import com.example.manager.Client.Infrastructure.Controller.Dto.Input.ClientInputDto;
import com.example.manager.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;
import com.example.manager.Client.Infrastructure.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<ClientOutputDto> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(ClientOutputDto::new).collect(Collectors.toList());
    }

    @Override
    public ClientOutputDto addClient(ClientInputDto clientInputDto) {
        Client client = clientInputDtoToEntity(clientInputDto);
        clientRepository.save(client);
        return new ClientOutputDto(client);
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
