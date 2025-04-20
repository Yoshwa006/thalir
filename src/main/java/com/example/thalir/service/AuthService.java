package com.example.thalir.service;



import com.example.thalir.dto.request.LoginRequest;
import com.example.thalir.dto.DTOMapper;
import com.example.thalir.dto.request.RegisterRequest;
import com.example.thalir.dto.responce.RegisterResponce;
import com.example.thalir.entity.Users;
import com.example.thalir.exceptions.EmailAlreadyExistsException;
import com.example.thalir.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        Users users = DTOMapper.toUser(request);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        repo.save(users);

    }

    public RegisterResponce login(LoginRequest loginRequest) throws UsernameNotFoundException {


       Users users = repo.findByEmail(loginRequest.getEmail());
       if(users == null){
           throw new UsernameNotFoundException("Email dont exist");
       }

        if (!passwordEncoder.matches(loginRequest.getPassword(), users.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(users.getEmail());
        System.out.println(token);
        RegisterResponce responce = new RegisterResponce();
        responce.setToken(token);
        responce.setEmail(users.getEmail());
        return responce;
    }
}
