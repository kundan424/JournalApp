package com.example.JournalApp.service;

import com.example.JournalApp.entity.UserEntry;
import com.example.JournalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntry user = userRepository.findByUsername(username);
        if (user != null){
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
        }
   throw new UsernameNotFoundException("user not found with username" + username);
    }
}

 */

// ... imports

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntry user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");

        // Convert List<String> roles to authorities
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role)) // e.g., "ROLE_USER"
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}

/*
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional // Prevents LazyInitializationException
    public UserDetails loadUserByUsername(String username) {
        UserEntry user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");

        // Ensure roles are prefixed with "ROLE_"
//        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())) // Converts "admin" -> "ROLE_ADMIN"
//                .collect(Collectors.toList());

        List<SimpleGrantedAuthority> authorities = Arrays.asList(
                new SimpleGrantedAuthority(user.getRole()) // Role should be ROLE_ADMIN
        );
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}

 */