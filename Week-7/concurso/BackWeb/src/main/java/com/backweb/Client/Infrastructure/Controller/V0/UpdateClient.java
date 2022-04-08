package com.backweb.Client.Infrastructure.Controller.V0;

import com.backweb.Client.Application.ClientService;
import com.backweb.Client.Infrastructure.Controller.Dto.Input.ClientInputDto;
import com.backweb.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("v0/client")
@RestController
public class UpdateClient {
    @Autowired
    ClientService clientService;

    @PutMapping("{id}")
    public ClientOutputDto updateTrip(@PathVariable UUID id, @RequestBody ClientInputDto clientInputDto) {
        return clientService.updateClient(id, clientInputDto);
    }
}
