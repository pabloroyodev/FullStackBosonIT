package com.backempresa.Client.Infrastructure.Controller.V0;

import com.backempresa.Client.Application.ClientService;
import com.backempresa.Client.Infrastructure.Controller.Dto.Input.ClientInputDto;
import com.backempresa.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;
import com.backempresa.Utils.Auth.AuthUtils;
import com.backempresa.Utils.Exceptions.customUnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("v0/client")
@RestController
public class UpdateClient {
    @Autowired
    ClientService clientService;

    @PutMapping("{id}")
    public ClientOutputDto updateTrip(@PathVariable UUID id, @RequestBody ClientInputDto clientInputDto, @RequestHeader("Authorization") String auth) {
        UUID idToken = AuthUtils.getId(auth);
        if (!idToken.equals(id)) {
            throw new customUnprocesableException("La persona autenticada no corresponde con al persona que quieres cambiar");
        }

        return clientService.updateClient(id, clientInputDto);
    }
}
