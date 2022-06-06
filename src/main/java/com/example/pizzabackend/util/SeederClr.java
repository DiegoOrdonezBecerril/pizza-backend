package com.example.pizzabackend.util;

import com.example.pizzabackend.entity.PizzaEntity;
import com.example.pizzabackend.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeederClr implements CommandLineRunner {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Override
    public void run(String... args) throws Exception {
        if(pizzaRepository.count() < 1){
            List<PizzaEntity> pizzas = new ArrayList<>();

            pizzas.add(PizzaEntity.builder().id(1L).name("Hawaiian").build());
            pizzas.add(PizzaEntity.builder().id(2L).name("Pepperoni").build());

            pizzaRepository.saveAll(pizzas);
        }
    }
}
