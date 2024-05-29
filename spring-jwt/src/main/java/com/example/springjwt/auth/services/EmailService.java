package com.example.springjwt.auth.services;


import com.example.springjwt.auth.entities.MailBody;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendSimpleMessage(MailBody mailBody) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailBody.to());
        System.out.println("radwa444444444444444");
        message.setFrom("rodymans25@gmail.com");
        message.setSubject(mailBody.subject());
        message.setText(mailBody.text());
        System.out.println("radwa444444444444444");

        javaMailSender.send(message);

        System.out.println("radwa444444444444444");
    }
}
