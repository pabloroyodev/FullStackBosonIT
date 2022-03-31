package com.example.manager.Client.Infrastructure.Repository;

import com.example.manager.Client.Domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
