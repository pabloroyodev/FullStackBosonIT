package com.backempresa.Mail.Infrastructure.Controller;

import com.backempresa.Mail.Application.MailService;
import com.backempresa.Mail.Domain.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("v0/mail")
@RestController
public class ReadMail {
    @Autowired
    MailService mailService;

    @GetMapping
    public List<Mail> findAll(){
        return mailService.getAllMail();
    }

    @GetMapping("{id}")
    public Mail filterMailById(@PathVariable UUID id) {
        return mailService.filterMailById(id);
    }

    @GetMapping("/details")
    public List<Mail> findByDepartureAndArrival(@RequestParam String departure, @RequestParam String arrival) {
        return mailService.findByDepartureAndArrival(departure, arrival);
    }

    @GetMapping("/detailsLocalDate")
    public List<Mail> findByLocalDate(@RequestParam String localDate) {
        return mailService.findByLocalDate(localDate);
    }
}
