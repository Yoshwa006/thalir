package com.example.thalir.repo;

import com.example.thalir.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    public boolean existsByEmail(String email);
}
