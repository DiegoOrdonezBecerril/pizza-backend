package com.example.pizzabackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailDTO {
    private String pizzaName;
    private int amount;
}
