package com.example.manager.Client.Infrastructure.Controller.V0;

import com.example.manager.Client.Application.ClientService;
import com.example.manager.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("v0/client")
@RestController
public class ReadClient {
    @Autowired
    ClientService clientService;

    @GetMapping
    public List<ClientOutputDto> findAll(){
        return clientService.getAllClients();
    }
}
