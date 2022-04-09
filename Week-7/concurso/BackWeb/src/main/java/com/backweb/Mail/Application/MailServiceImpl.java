package com.backweb.Mail.Application;

import com.backweb.Mail.Domain.Mail;
import com.backweb.Mail.Infrastructure.Repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MailServiceImpl implements MailService{

    @Autowired
    MailRepository mailRepository;

    @Override
    public List<Mail> getAllMail() {
        List<Mail> mails = mailRepository.findAll();
        return mails.stream().collect(Collectors.toList());
    }

    @Override
    public Mail filterMailById(UUID id) {
        return mailRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Mail> findByDepartureAndArrival(String departure, String arrival) {
        return null;
    }

    @Override
    public List<Mail> findByLocalDate(String date) {
        return null;
    }
}
