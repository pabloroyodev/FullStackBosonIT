package com.backempresa.Mail.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Mail {
    @Id @Column(name = "id_mail")
    private UUID idMail;

    private Date date;

    private String departure;

    private String arrival;

    private String to;

    private String subject;
}
