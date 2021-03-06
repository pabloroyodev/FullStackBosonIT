package com.backweb.Client.Infrastructure.Controller.V0;

import com.backweb.Client.Application.ClientService;
import com.backweb.Client.Infrastructure.Controller.Dto.Input.ClientInputDto;
import com.backweb.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("v0/client")
@RestController
public class CreateClient {
    @Autowired
    ClientService clientService;

    @PostMapping
    public ClientOutputDto addClient(@RequestBody ClientInputDto clientInputDto) {
        return clientService.addClient(clientInputDto);
    }
}
