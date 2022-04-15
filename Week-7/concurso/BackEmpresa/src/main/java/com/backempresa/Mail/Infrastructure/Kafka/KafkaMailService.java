package com.backempresa.Mail.Infrastructure.Kafka;

import com.backempresa.Mail.Domain.Mail;
import com.backempresa.Mail.Infrastructure.Repository.MailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMailService {
    @Autowired
    MailRepository mailRepository;

    public void listenTopic(String action, Mail mail) {
        switch (action) {
            case "create" -> {
                mailRepository.save(mail);

                log.info("CREATE SUCCESS");
            }
            default -> {

                log.info("ERROR KAFKA SERVICE MAIL! ACCION NO ESPECIFICADA (create)");
            }
        }
    }
}
