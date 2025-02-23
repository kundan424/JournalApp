

package com.example.JournalApp.service;

import com.example.JournalApp.schedular.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SentimentAnalysisServiceTest {

    @Autowired
    private UserScheduler userScheduler;

    @Autowired
    private  EmailService emailService;

    @Test
//    tested correctly
    public void testSa(){
    userScheduler.fetchUsersAndSendSaMail();
    }
}
