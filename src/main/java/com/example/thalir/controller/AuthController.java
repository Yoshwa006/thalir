package com.example.thalir.controller;


import com.example.thalir.dto.request.LoginRequest;
import com.example.thalir.dto.request.RegisterRequest;
import com.example.thalir.dto.responce.RegisterResponce;
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

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Registration successful");
    }

    @PostMapping("/login")
    public ResponseEntity<RegisterResponce> login(@RequestBody LoginRequest loginRequest) {
        RegisterResponce responce = authService.login(loginRequest);
        return ResponseEntity.ok().body(responce);
    }
}
