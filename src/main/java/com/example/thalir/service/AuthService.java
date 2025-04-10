package com.example.thalir.service;



import com.example.thalir.dto.LoginRequest;
import com.example.thalir.dto.MapperDTO;
import com.example.thalir.dto.RegisterRequest;
import com.example.thalir.exception.EmailAlreadyExistsException;
import com.example.thalir.model.User;
import com.example.thalir.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.thalir.exception.InvalidCredentialsException;

@Service
public class AuthService {

    private final UserRepo repo;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public AuthService(UserRepo repo, BCryptPasswordEncoder passwordEncoder){
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request){
        if(repo.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists: " + request.getEmail());
        }
        User user = MapperDTO.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);

    }

    public void login(LoginRequest loginRequest) {
       User user = repo.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new InvalidCredentialsException("Invalid email or password "));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

    }
}
