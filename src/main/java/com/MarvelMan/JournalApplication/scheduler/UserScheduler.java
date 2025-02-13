package com.MarvelMan.JournalApplication.scheduler;

import com.MarvelMan.JournalApplication.cache.AppCache;
import com.MarvelMan.JournalApplication.entity.JournalEntry;
import com.MarvelMan.JournalApplication.entity.User;
import com.MarvelMan.JournalApplication.enums.Sentiment;
import com.MarvelMan.JournalApplication.model.SentimentData;
import com.MarvelMan.JournalApplication.repository.UserRepositoryImpl;
import com.MarvelMan.JournalApplication.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

@Autowired
    EmailService emailService;
    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private AppCache appCache;

    public void fetchUsersAndSendSaMail() {
        List<User> users = userRepository.getUserForSA();
        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null)
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if (mostFrequentSentiment != null) {
                SentimentData data=SentimentData.builder().email(user.getEmail()).sentiment("Sentiment for last 7 days "+mostFrequentSentiment.toString()).build();
              //  kafkaTemplate.send("weekly-sentiments",user.getEmail(),data);
                emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days ", mostFrequentSentiment.toString());
            }
        }
    }
}