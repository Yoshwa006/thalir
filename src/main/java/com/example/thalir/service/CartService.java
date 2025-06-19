package com.example.thalir.service;

import com.example.thalir.dto.responce.CartModelDTO;
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

import java.util.List;
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

    public String addToCart(String email, Long modelId) {
        Users user = userRepo.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        Model model = modelRepo.findById(modelId)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found"));

        Optional<CartItem> alreadyExists = cartRepo.findByUserAndModel(user, model);
        if (alreadyExists.isPresent()) {
            throw new IllegalStateException("Item is already in your cart");
        }

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setModel(model);
        cartRepo.save(cartItem);

        return "Item successfully added to cart";
    }



    public List<CartModelDTO> getCartItems(String email) throws ResourceNotFoundException {
        Users user = userRepo.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        List<CartItem> cartItems = cartRepo.findByUser(user);
        return cartItems.stream()
                .map(item -> new CartModelDTO(item.getModel()))
                .toList();
    }

    public String removeFromCart(String email, Long modelId) {
        Users user = userRepo.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        Model model = modelRepo.findById(modelId)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found"));

        CartItem cartItem = cartRepo.findByUserAndModel(user, model)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found in cart"));

        cartRepo.delete(cartItem);
        return "Item removed from cart";
    }


}
