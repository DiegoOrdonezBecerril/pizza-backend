package com.example.pizzabackend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @ManyToOne(optional = false)
    private OrderEntity order;
    @ManyToOne(optional = false)
    private PizzaEntity pizza;
    @Column(nullable = false)
    private int amount;
}
