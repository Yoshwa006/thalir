package com.example.thalir.service;

import com.example.thalir.entity.Users;
import com.example.thalir.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {


    private UserRepository repo;

    @Autowired
    public CustomUserDetailService(UserRepository repo){
        this.repo =repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        // Fetch the user from the database
        Users users = repo.findByEmail(email);

        if (users == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Return a UserDetails implementation (Spring Security's User)
        return users;
    }
}
