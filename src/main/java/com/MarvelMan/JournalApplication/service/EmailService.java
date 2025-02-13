package com.MarvelMan.JournalApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

   @Autowired
JavaMailSender mailSender;
    public void sendEmail(String to,String subject ,String body){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setSubject(subject);
        mailMessage.setTo(to);
        mailMessage.setText(body);
        mailSender.send(mailMessage);
    }
}

