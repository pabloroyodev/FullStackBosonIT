package com.backweb.Client.Infrastructure.Controller.V0;

import com.backweb.Client.Application.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("v0/client")
@RestController
public class DeleteClient {
    @Autowired
    ClientService clientService;

    @DeleteMapping("{id}")
    public void deleteTrip(@PathVariable UUID id) {
        clientService.deleteClient(id);
    }
}
