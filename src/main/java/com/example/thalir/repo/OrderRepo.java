package com.example.thalir.repo;

import com.example.thalir.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order , Long> {
}
