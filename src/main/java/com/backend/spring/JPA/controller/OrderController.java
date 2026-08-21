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
@RequestMapping("/api/v1/users/{userId}/orders")
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

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderModel> updateOrder(@RequestBody CreateOrderModel updateOrderModel,
                                                  @PathVariable Long userId,@PathVariable Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrder(updateOrderModel, userId, orderId));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable Long userId, @PathVariable Long orderId) {
        boolean isDeleted = orderService.deleteOrder(userId, orderId);

        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
