package com.example.thalir.controller;


import com.example.thalir.dto.request.AddToCartRequest;
import com.example.thalir.dto.responce.CartModelDTO;
import com.example.thalir.entity.Model;
import com.example.thalir.exceptions.ResourceNotFoundException;
import com.example.thalir.service.CartService;
import com.example.thalir.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final JwtService jwtService;
    private final CartService cartService;

    @Autowired
    CartController(JwtService service, CartService cartService){
        this.jwtService = service;
        this.cartService = cartService;
    }


    @GetMapping
    public ResponseEntity<List<CartModelDTO>> getCartItems(@RequestHeader("Authorization") String token) {
        String email = jwtService.extractUsername(token.substring(7)); // remove "Bearer "
        try {
            List<CartModelDTO> cartItems = cartService.getCartItems(email);
            return ResponseEntity.ok(cartItems);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestHeader("Authorization") String token,
                                       @RequestBody AddToCartRequest request) {
        try {
            String email = jwtService.extractUsername(token.replace("Bearer ", ""));
            String result = cartService.addToCart(email, request.getModelId());
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
        }
    }
    @DeleteMapping("/remove")
    public ResponseEntity<?> removeFromCart(@RequestHeader("Authorization") String token,
                                            @RequestBody AddToCartRequest request) {
        try {
            String email = jwtService.extractUsername(token.replace("Bearer ", ""));
            String result = cartService.removeFromCart(email, request.getModelId());
            return ResponseEntity.ok(result);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
        }
    }



}
