package com.example.JournalApp.useRepository;

import com.example.JournalApp.repository.UserRepository;
import com.example.JournalApp.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    public void testSaveNewUser() {
        userRepository.getUserForSA();
    }
}
