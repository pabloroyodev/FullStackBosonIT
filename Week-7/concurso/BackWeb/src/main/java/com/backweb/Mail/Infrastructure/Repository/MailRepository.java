package com.backweb.Mail.Infrastructure.Repository;

import com.backweb.Mail.Domain.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MailRepository extends JpaRepository<Mail, UUID> {
}
