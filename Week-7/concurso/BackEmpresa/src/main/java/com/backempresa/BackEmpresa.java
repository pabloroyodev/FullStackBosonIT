package com.backempresa;

import com.backempresa.Client.Application.ClientServiceImpl;
import com.backempresa.Client.Infrastructure.Controller.Dto.Input.ClientInputDto;
import com.backempresa.Client.Infrastructure.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackEmpresa {
	@Autowired
	ClientRepository clientRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackEmpresa.class, args);
	}

	@Bean
	CommandLineRunner run(ClientServiceImpl clientService){
		return args -> {
			if (clientRepository.findByEmail("admin@adminbus.local") == null) {
				clientService.addClient(new ClientInputDto("Admin", "Bus", "admin@adminbus.local", "1234"));
			}
		};
	}

}
