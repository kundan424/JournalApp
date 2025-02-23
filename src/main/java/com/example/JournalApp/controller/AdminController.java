package com.example.JournalApp.controller;

import com.example.JournalApp.cache.AppCache;
import com.example.JournalApp.entity.UserEntry;
import com.example.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppCache appCache;

    @GetMapping("/all-users")
    public ResponseEntity<List<UserEntry>> getAllUsers(){
        List<UserEntry> all = userService.getAll();
        if (all != null && !all.isEmpty()){
            return new ResponseEntity<>(all , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody UserEntry user) {
        userService.saveAdmin(user);
    }

    @GetMapping("/clear-app-cache")
    public void  clearCache(){
        appCache.init();
    }
}
