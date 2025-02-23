package com.example.JournalApp.service;

import com.example.JournalApp.entity.UserEntry;
import com.example.JournalApp.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
/*
more annotations
    @AfterAll
    @AfterEach


 */
    @Disabled("Tested")
   @ParameterizedTest
   @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(UserEntry user){
//        assertNotNull(userRepository.findByUsername(name));
   assertTrue(userService.saveNewUser(user));
   }


/*
    @Disabled
   @ParameterizedTest
   @CsvSource({
         "1 , 1, 2",
          "2 , 4, 5",
          " 2, 5, 7"
   })
    public void test ( int a , int b , int expected){
        assertEquals(expected , a + b);
    }
 */

}
