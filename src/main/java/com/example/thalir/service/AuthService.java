package com.example.thalir.service;



import com.example.thalir.dto.request.LoginRequest;
import com.example.thalir.dto.DTOMapper;
import com.example.thalir.dto.request.RegisterRequest;
import com.example.thalir.dto.responce.RegisterResponce;
import com.example.thalir.entity.Users;
import com.example.thalir.exceptions.EmailAlreadyExistsException;
import com.example.thalir.exceptions.EmailNotFoundException;
import com.example.thalir.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.thalir.exceptions.InvalidCredentialsException;

@Service
public class AuthService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;


    @Autowired
    public AuthService(UserRepository repo, BCryptPasswordEncoder passwordEncoder){
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request){
        if(repo.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists: " + request.getEmail());
        }
        Users users = DTOMapper.toUser(request);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        repo.save(users);

    }

    public RegisterResponce login(LoginRequest loginRequest) throws EmailNotFoundException {


       Users users = repo.findByEmail(loginRequest.getEmail());
       if(users == null){
           throw new EmailNotFoundException("Email don't exist");
       }

        if (!passwordEncoder.matches(loginRequest.getPassword(), users.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtService.generateToken(users.getEmail());
        System.out.println(token);
        RegisterResponce responce = new RegisterResponce();
        responce.setToken(token);
        responce.setEmail(users.getEmail());
        return responce;
    }
}
