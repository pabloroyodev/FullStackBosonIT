package com.backweb.Mail.Infrastructure.Kafka;

import com.backweb.Mail.Domain.Mail;
import com.backweb.Mail.Infrastructure.Repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaMailService {
    @Autowired
    MailRepository mailRepository;

    public void listenTopic(String action, Mail mail) {
        switch (action) {
            case "create" -> {
                System.out.println(mail);
                mailRepository.save(mail);
                System.out.println("CREATE SUCCESS");
            }
        }
    }
}
