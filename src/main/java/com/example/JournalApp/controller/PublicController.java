package com.example.JournalApp.controller;

import com.example.JournalApp.entity.UserEntry;
import com.example.JournalApp.service.UserDetailsServiceImpl;
import com.example.JournalApp.service.UserService;
import com.example.JournalApp.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Ok";
    }
/*
    @PostMapping("/create-user")
    public void createUser(@RequestBody UserEntry user){
        userService.saveNewUser(user);
    }
 */

    @PostMapping("/signup")
    public void  SingUp(@RequestBody UserEntry user){
        userService.saveNewUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody UserEntry user){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername() , user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
        log.error("Exception occurred while create authenticationToken " , e);
        return new ResponseEntity<>("incorrect username or password " , HttpStatus.BAD_REQUEST);
        }
    }
}
