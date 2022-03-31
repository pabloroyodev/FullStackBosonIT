package com.example.manager.Ticket.Infrastructure.Repository;

import com.example.manager.Ticket.Domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
