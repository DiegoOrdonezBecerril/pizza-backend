package com.example.pizzabackend.repository;

import com.example.pizzabackend.entity.PizzaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<PizzaEntity, Long> {
    PizzaEntity findByName(String name);
}
