package com.example.thalir.repository;

import com.example.thalir.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Long> {
    boolean existsByEmail(String email);
    Users findByEmail(String username);
}
