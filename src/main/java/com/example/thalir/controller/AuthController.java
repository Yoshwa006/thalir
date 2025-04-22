package com.example.thalir.controller;


import com.example.thalir.dto.request.LoginRequest;
import com.example.thalir.dto.request.RegisterRequest;
import com.example.thalir.dto.responce.RegisterResponce;
import com.example.thalir.exceptions.EmailNotFoundException;
import com.example.thalir.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthService authService;

    @Autowired
    public AuthController(AuthService service){
        this.authService = service;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Registration successful");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            RegisterResponce response = authService.login(loginRequest);
            return ResponseEntity.ok().body(response);
        } catch (EmailNotFoundException e) {
           return ResponseEntity.status(404).body("Model not found with ID: " + loginRequest.getEmail());
        }
    }
}
