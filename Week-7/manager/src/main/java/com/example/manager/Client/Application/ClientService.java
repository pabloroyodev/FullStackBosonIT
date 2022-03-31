package com.example.manager.Client.Application;

import com.example.manager.Client.Infrastructure.Controller.Dto.Input.ClientInputDto;
import com.example.manager.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;

import java.util.List;

public interface ClientService {
    List<ClientOutputDto> getAllClients();
    //ClientOutputDto filterClientById(Integer id);
    //ClientOutputDto filterClientByEmail(String email);
    ClientOutputDto addClient(ClientInputDto clientInputDto);
    //ClientOutputDto updateClient(Integer id, ClientInputDto clientInputDto);
    //void deleteClient(Integer id);
}
