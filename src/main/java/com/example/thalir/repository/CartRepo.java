package com.example.thalir.repository;

import com.example.thalir.entity.CartItem;
import com.example.thalir.entity.Model;
import com.example.thalir.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepo extends JpaRepository<CartItem ,Long> {
    Optional<CartItem> findByUserAndModel(Users user, Model model);
    List<CartItem> findByUser(Users user);
}
