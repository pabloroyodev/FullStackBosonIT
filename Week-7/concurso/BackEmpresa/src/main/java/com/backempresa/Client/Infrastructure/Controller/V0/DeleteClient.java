package com.backempresa.Client.Infrastructure.Controller.V0;

import com.backempresa.Client.Application.ClientService;
import com.backempresa.Utils.Exceptions.customUnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("v0-empresa/client")
@RestController
public class DeleteClient {
    @Autowired
    ClientService clientService;

    @DeleteMapping("{id}")
    public void deleteTrip(@PathVariable UUID id) {
        if (clientService.filterClientByEmail("admin@adminbus.local").getIdClient().equals(id)) {
            throw new customUnprocesableException("No se puede eliminar administrador");
        }
        clientService.deleteClient(id);
    }
}
