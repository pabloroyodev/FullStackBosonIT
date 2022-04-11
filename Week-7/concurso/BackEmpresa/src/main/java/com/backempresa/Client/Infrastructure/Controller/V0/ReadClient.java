package com.backempresa.Client.Infrastructure.Controller.V0;

import com.backempresa.Client.Application.ClientService;
import com.backempresa.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;
import com.backempresa.Utils.Auth.AuthUtils;
import com.backempresa.Utils.Exceptions.customUnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("v0-empresa/client")
@RestController
public class ReadClient {
    @Autowired
    ClientService clientService;

    @GetMapping
    public List<ClientOutputDto> findAll(){
        return clientService.getAllClients();
    }

    @GetMapping("{id}")
    public ClientOutputDto filterClientById(@PathVariable UUID id, @RequestHeader("Authorization") String auth) {
        UUID idToken = AuthUtils.getId(auth);
        if (!idToken.equals(id)) {
            throw new customUnprocesableException("La persona autenticada no corresponde con al persona que quieres leer");
        }

        return clientService.filterClientById(id);
    }

    @GetMapping("{email}/email")
    public ClientOutputDto filterClientByEmail(@PathVariable String email){
        return clientService.filterClientByEmail(email);
    }
}
