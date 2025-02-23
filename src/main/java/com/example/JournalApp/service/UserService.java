package com.example.JournalApp.service;

import com.example.JournalApp.entity.UserEntry;
import com.example.JournalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//     private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public boolean saveNewUser(UserEntry user) {
       try {
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           user.setRoles(List.of("ROLE_USER")); // Use List.of()
           userRepository.save(user);
           return true;
       }catch (Exception e){
//           logger.info("Galti kar rhe ho tum!");
           log.info("Galti kar rhe ho tum!");
           log.debug("Galti kar rhe ho tum!");
           log.warn("Galti kar rhe ho tum!");
           log.error("Galti kar rhe ho tum!");

           return false;
       }
    }

    public void saveAdmin(UserEntry user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("ROLE_ADMIN" , "ROLE_USER"));
        userRepository.save(user);
    }


    public void saveUser(UserEntry userEntry){
        userRepository.save(userEntry);
    }

    public List<UserEntry> getAll(){
        return userRepository.findAll();
    }

    public Optional<UserEntry> findById(@PathVariable ObjectId myId){
        return userRepository.findById(myId);
    }

    public void deleteById(@PathVariable ObjectId myId){
         userRepository.deleteById(myId);
    }

    public UserEntry findByUsername(String userName){
       return userRepository.findByUsername(userName);
    }
}
