package com.example.thalir.service;



import com.example.thalir.dto.request.LoginRequest;
import com.example.thalir.dto.DTOMapper;
import com.example.thalir.dto.request.RegisterRequest;
import com.example.thalir.dto.responce.RegisterResponce;
import com.example.thalir.exceptions.EmailAlreadyExistsException;
import com.example.thalir.entity.User;
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
    private JwtService jwtUtil;


    @Autowired
    public AuthService(UserRepository repo, BCryptPasswordEncoder passwordEncoder){
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request){
        if(repo.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists: " + request.getEmail());
        }
        User user = DTOMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);

    }

    public RegisterResponce login(LoginRequest loginRequest) {


       User user = repo.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new InvalidCredentialsException("Invalid email or password "));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        System.out.println(token);
        RegisterResponce responce = new RegisterResponce();
        responce.setToken(token);
        responce.setMessage("Login Successful !");
        responce.setEmail(user.getEmail());
        return responce;
    }
}
