package com.example.JournalApp.schedular;

import com.example.JournalApp.entity.JournalEntry;
import com.example.JournalApp.entity.UserEntry;
import com.example.JournalApp.enums.Sentiment;
import com.example.JournalApp.repository.UserRepository;
import com.example.JournalApp.repository.UserRepositoryImpl;
import com.example.JournalApp.service.EmailService;
//import com.example.JournalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserScheduler {

    @Autowired
    private UserRepositoryImpl userRepository;


    @Autowired
    private UserRepository userRepository1; // ✅ Inject the repository


    @Autowired
    private EmailService emailService;
//
//    @Autowired
//    private Sentiment sentiment;

    /*
    @Scheduled(cron = "0 * * ? * *")
    public void fetchUsersAndSendSaMail() {
        List<UserEntry> users = userRepository.getUserForSA();
        for (UserEntry user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getContent()).collect(Collectors.toList());
            String entry = String.join(" ", filteredEntries);
            int sentiment = sentimentAnalysisService.getSentiment(entry);
            emailService.sendEmail(user.getEmail() , "sentiment for last seven days" , String.valueOf(sentiment));
        }
    } */

//    @Scheduled(cron = "0 * * ? * *") // Runs at the start of every minute
//    public void fetchUsersAndSendSaMail(){
//        List<UserEntry> users = userRepository.getUserForSA();
//        for (UserEntry user : users){
//            List<JournalEntry> journalEntries = user.getJournalEntries();
//            List<String> filteredEntries = journalEntries.stream().map(JournalEntry::getContent).collect(Collectors.toList());
//            String entry = String.join(" ", filteredEntries);
//            int sentiment = sentimentAnalysisService.getSentiment(entry);
//            emailService.sendEmail(user.getEmail() ,"Sentiment Analysis Update",
//                    "Your sentiment score based on journal entries: " + sentiment);
//        }
//    }


    @Scheduled(cron = "0 * * ? * *") // Runs at the start of every minute
    public void fetchUsersAndSendSaMail(){
        List<UserEntry> users = userRepository.getUserForSA();
        for (UserEntry user : users){
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment , Integer>  sentimentCount  = new HashMap<>();
            for (Sentiment sentiment : sentiments){
                if( sentiment != null){
                    sentimentCount.put(sentiment , sentimentCount.getOrDefault(sentiment , 0) + 1);
                }
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0 ;
            for (Map.Entry<Sentiment , Integer> entry : sentimentCount.entrySet()){
                if (entry.getValue() > maxCount){
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            if ( mostFrequentSentiment != null){
                emailService.sendEmail(user.getEmail(), "Sentiment analysis of 7 days" , "you are feeling " + mostFrequentSentiment +" in this week");
            }

        }
    }
}

/*
if autowired userEntry instead of userRepository , it gives "No qualifying bean of type 'com.example.JournalApp.entity.UserEntry' available
" error

Why Did Injecting UserEntryRepository Fix the Issue?
Spring manages repository interfaces (like UserEntryRepository) as beans because they extend JpaRepository.
JPA entities (@Entity) are not Spring-managed beans, so injecting them directly doesn't work.
By injecting UserEntryRepository, you get access to database operations, making the code work as expected.
 */
