package com.backweb.Client.Infrastructure.Controller.V0;

import com.backweb.Client.Application.ClientService;
import com.backweb.Client.Infrastructure.Controller.Dto.Input.ClientInputDto;
import com.backweb.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;
import com.backweb.Utils.Auth.AuthUtils;
import com.backweb.Utils.Exceptions.customUnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RequestMapping("v0/client")
@RestController
public class UpdateClient {
    @Autowired
    ClientService clientService;

    @Value("${urlempresa}")
    String EMPRESA;

    @PutMapping("{id}")
    public ClientOutputDto updateClient(@PathVariable UUID id, @RequestBody ClientInputDto clientInputDto, @RequestHeader("Authorization") String auth) {
        HttpEntity<Object> request = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Void> re = new RestTemplate().exchange(EMPRESA + "/" + auth, HttpMethod.GET, request, Void.class);

        if (re.getStatusCode()== HttpStatus.OK) {
            UUID idToken = AuthUtils.getId(auth);
            if (!idToken.equals(id)) {
                System.out.println(idToken);
                System.out.println(id);
                throw new customUnprocesableException("La persona autenticada no corresponde con al persona que quieres cambiar");
            }
            return clientService.updateClient(id, clientInputDto);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Autenticacion incorrecta");
    }
}
