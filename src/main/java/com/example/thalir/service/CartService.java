package com.example.thalir.service;

import com.example.thalir.entity.CartItem;
import com.example.thalir.entity.Model;
import com.example.thalir.entity.Users;
import com.example.thalir.exceptions.ResourceNotFoundException;
import com.example.thalir.repository.CartRepo;
import com.example.thalir.repository.ModelRepository;
import com.example.thalir.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    private final CartRepo cartRepo;
    private final UserRepository userRepo;
    private final ModelRepository modelRepo;

    @Autowired
    public CartService(CartRepo cartRepo, UserRepository userRepo, ModelRepository modelRepo){
        this.cartRepo =cartRepo;
        this.modelRepo =modelRepo;
        this.userRepo = userRepo;
    }

    public void addToCart(String email, Long modelId) throws ResourceNotFoundException {

        Users user = userRepo.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("Email not found!");
        }


        Model model = modelRepo.findById(modelId)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found!"));


        Optional<CartItem> alreadyExists = cartRepo.findByUserAndModel(user, model);
        if (alreadyExists.isPresent()) {

            ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Item is already in your cart!");
            return;
        }


        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setModel(model);
        cartRepo.save(cartItem);


        ResponseEntity.status(HttpStatus.CREATED)
                .body("Item successfully added to cart!");
    }

}
