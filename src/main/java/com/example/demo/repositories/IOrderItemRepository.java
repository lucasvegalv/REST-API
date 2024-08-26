package com.example.demo.repositories;

import com.example.demo.entities.OrderItemEntity;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderItemRepository extends JpaRepository<OrderItemEntity, Integer> {
}
