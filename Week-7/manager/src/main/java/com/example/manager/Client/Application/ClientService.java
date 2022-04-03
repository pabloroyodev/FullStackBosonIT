package com.example.manager.Client.Application;

import com.example.manager.Client.Infrastructure.Controller.Dto.Input.ClientInputDto;
import com.example.manager.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    List<ClientOutputDto> getAllClients();
    ClientOutputDto filterClientById(UUID id);
    ClientOutputDto filterClientByEmail(String email);
    ClientOutputDto addClient(ClientInputDto clientInputDto);
    ClientOutputDto updateClient(UUID id, ClientInputDto clientInputDto);
    void deleteClient(UUID id);
}
