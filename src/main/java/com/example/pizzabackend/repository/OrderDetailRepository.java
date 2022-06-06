package com.example.pizzabackend.repository;

import com.example.pizzabackend.entity.OrderDetailEntity;
import com.example.pizzabackend.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
    List<OrderDetailEntity> findByOrder(OrderEntity order);
}
