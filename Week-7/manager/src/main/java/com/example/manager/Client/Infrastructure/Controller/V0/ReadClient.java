package com.example.manager.Client.Infrastructure.Controller.V0;

import com.example.manager.Client.Application.ClientService;
import com.example.manager.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequestMapping("v0/client")
@RestController
public class ReadClient {
    @Autowired
    ClientService clientService;

    @GetMapping
    public List<ClientOutputDto> findAll(){
        return clientService.getAllClients();
    }

    @GetMapping("{id}")
    public ClientOutputDto filterClientById(@PathVariable UUID id){
        return clientService.filterClientById(id);
    }

    @GetMapping("{email}/email")
    public ClientOutputDto filterClientByEmail(@PathVariable String email){
        return clientService.filterClientByEmail(email);
    }
}
