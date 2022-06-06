package com.example.pizzabackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class HistoryDTO {
    private String date;
    private List<String> description;
}
