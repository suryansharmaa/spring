package com.backend.spring.JPA.controller;

import com.backend.spring.JPA.model.CreateOrderModel;
import com.backend.spring.JPA.model.OrderModel;
import com.backend.spring.JPA.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users/{id}/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderModel> createOrders(@RequestBody CreateOrderModel createOrderModel, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrders(createOrderModel, id));
    }

    @GetMapping
    public ResponseEntity<List<OrderModel>> getOrdersById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersById(id));
    }
}
