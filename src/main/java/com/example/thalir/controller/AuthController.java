package com.example.thalir.controller;


import com.example.thalir.dto.RegisterRequest;
import com.example.thalir.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> getLoginData(@RequestBody RegisterRequest request){
        service.saveLoginData(request);
        return ResponseEntity.ok().body(request);
    }
}
