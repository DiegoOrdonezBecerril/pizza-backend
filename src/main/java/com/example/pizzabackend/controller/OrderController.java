package com.example.pizzabackend.controller;

import com.example.pizzabackend.dto.HistoryDTO;
import com.example.pizzabackend.service.OrderService;
import com.example.pizzabackend.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<Void> insert(@Validated @RequestBody OrderDTO orderDTO){
        return orderService.insert(orderDTO);
    }

    @GetMapping("/history")
    public ResponseEntity<List<HistoryDTO>> getHistory(){
        return orderService.getHistory();
    }
}
