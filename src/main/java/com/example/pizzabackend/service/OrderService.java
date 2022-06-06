package com.example.pizzabackend.service;

import com.example.pizzabackend.dto.HistoryDTO;
import com.example.pizzabackend.dto.OrderDTO;
import com.example.pizzabackend.dto.OrderDetailDTO;
import com.example.pizzabackend.entity.OrderDetailEntity;
import com.example.pizzabackend.entity.OrderEntity;
import com.example.pizzabackend.repository.OrderDetailRepository;
import com.example.pizzabackend.repository.OrderRepository;
import com.example.pizzabackend.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailsRepository;
    @Autowired
    private PizzaRepository pizzaRepository;

    public ResponseEntity<Void> insert(OrderDTO orderDTO){
        try{
            OrderEntity orderEntity = orderRepository.save(OrderEntity.builder().date(LocalDateTime.now()).build());

            List<OrderDetailEntity> orderDetailsEntities = orderDTO.getDetails().stream()
                    .map(orderDetailDTO -> OrderDetailEntity.builder()
                            .order(orderEntity)
                            .pizza(pizzaRepository.findByName(orderDetailDTO.getPizzaName()))
                            .amount(orderDetailDTO.getAmount())
                            .build()
                    ).collect(Collectors.toList());

            orderDetailsRepository.saveAll(orderDetailsEntities);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<HistoryDTO>> getHistory(){
        try{
            List<HistoryDTO> historyDTOS;
            List<OrderEntity> orderEntities = orderRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));

            historyDTOS = orderEntities.stream().map(orderEntity -> {
                HistoryDTO historyDTO = new HistoryDTO();
                historyDTO.setDate(orderEntity.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                historyDTO.setDescription(orderDetailsRepository.findByOrder(orderEntity).stream()
                        .map(orderDetailEntity -> orderDetailEntity.getAmount() + " x " + orderDetailEntity.getPizza().getName()
                        ).collect(Collectors.toList()));
                return historyDTO;
            }).collect(Collectors.toList());

            return new ResponseEntity<>(historyDTOS, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
