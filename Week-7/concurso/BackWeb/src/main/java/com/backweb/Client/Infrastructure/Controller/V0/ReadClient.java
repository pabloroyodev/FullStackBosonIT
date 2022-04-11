package com.backweb.Client.Infrastructure.Controller.V0;

import com.backweb.Client.Application.ClientService;
import com.backweb.Client.Infrastructure.Controller.Dto.Output.ClientOutputDto;
import com.backweb.Utils.Auth.AuthUtils;
import com.backweb.Utils.Exceptions.customUnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RequestMapping("v0/client")
@RestController
public class ReadClient {
    @Autowired
    ClientService clientService;

    @Value("${urlempresa}")
    String EMPRESA;
    /*
    @GetMapping
    public List<ClientOutputDto> findAll(){
        return clientService.getAllClients();
    }
    */

    @GetMapping("{id}")
    public ClientOutputDto filterClientById(@PathVariable UUID id, @RequestHeader("Authorization") String auth){
        HttpEntity<Object> request = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<Void> re = new RestTemplate().exchange(EMPRESA + "/" + auth, HttpMethod.GET, request, Void.class);

        if (re.getStatusCode()== HttpStatus.OK) {
            UUID idToken = AuthUtils.getId(auth);
            if (!idToken.equals(id)) {
                throw new customUnprocesableException("La persona autenticada no corresponde con al persona que quieres leer");
            }
            return clientService.filterClientById(id);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Autenticacion incorrecta");
    }

    /*
    @GetMapping("{email}/email")
    public ClientOutputDto filterClientByEmail(@PathVariable String email){
        return clientService.filterClientByEmail(email);
    }
    */
}
