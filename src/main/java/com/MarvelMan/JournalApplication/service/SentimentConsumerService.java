package com.MarvelMan.JournalApplication.service;

import com.MarvelMan.JournalApplication.model.SentimentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SentimentConsumerService {

//    @Autowired
//    EmailService emailService;
//     @KafkaListener(topics = "weekly-sentiments" ,groupId = "weekly-sentiment-group")
//    public void consume(SentimentData sentimentData){
//    emailService.sendEmail(sentimentData.getEmail(),"Sentiments",sentimentData.getSentiment());
//    }
}
