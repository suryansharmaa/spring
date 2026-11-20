package com.backend.spring.JPA.controller;

import com.backend.spring.JPA.model.CreateOrderModel;
import com.backend.spring.JPA.model.OrderModel;
import com.backend.spring.JPA.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderModel> createOrders(@RequestBody CreateOrderModel createOrderModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrders(createOrderModel));
    }

    // User -> my orders
    @GetMapping("/my")
    public ResponseEntity<List<OrderModel>> getOrdersById() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersById());
    }

    // User -> specific order
    @GetMapping("/my/{userId}")
    public ResponseEntity<OrderModel> getOrdersById(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersById(userId));
    }

    // Admin -> all orders
    @GetMapping
    public ResponseEntity<List<OrderModel>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // Admin -> Orders by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderModel>> getOrderByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrderByUser(userId));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
