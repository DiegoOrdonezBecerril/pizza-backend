package com.example.pizzabackend.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderDTO {
    private List<OrderDetailDTO> details;
}
