package com.backend.spring.JPA.service;

import com.backend.spring.JPA.entities.Orders;
import com.backend.spring.JPA.entities.Users;
import com.backend.spring.JPA.exception.UserNotFoundException;
import com.backend.spring.JPA.model.CreateOrderModel;
import com.backend.spring.JPA.model.OrderModel;
import com.backend.spring.JPA.model.UserModel;
import com.backend.spring.JPA.repository.OrderRepository;
import com.backend.spring.JPA.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.query.Order;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private Users getLoggedInUser() {
        String email = Objects.requireNonNull(SecurityContextHolder.getContext()
                .getAuthentication())
                .getName();

        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    private OrderModel map(Orders order) {
        Users u = order.getUser();

        return new OrderModel(
                order.getId(),
                order.getProductName(),
                new UserModel(u.getId(), u.getName(), u.getEmail())
        );
    }

    @Transactional
    public OrderModel createOrders(CreateOrderModel createOrderModel) {
        Users user = getLoggedInUser();

        Orders order = new Orders();
        order.setUser(user);
        order.setProductName(createOrderModel.getProductName());

        Orders savedOrder = orderRepository.save(order);

        return map(savedOrder);
    }

    public List<OrderModel> getOrdersById() {
        Users user = getLoggedInUser();

        return orderRepository.findByUserId(user.getId())
                .stream()
                .map(this::map)
                .toList();
    }

    public OrderModel getOrdersById(Long orderId) {

        Users user = getLoggedInUser();

        Orders order = orderRepository.findByIdAndUserId(
                orderId, user.getId()
        ).orElseThrow(() -> new RuntimeException("Order not found"));

        return map(order);
    }

    @Transactional
    public @Nullable OrderModel updateOrder(CreateOrderModel updateOrderModel, Long userId, Long orderId) {
        Orders order = orderRepository.findById(orderId).orElseThrow();
        order.setProductName(updateOrderModel.getProductName());

        if (!order.getUser().getId().equals(userId)) {
            return null;
        }

        return new OrderModel(order.getId(), order.getProductName(), new UserModel(
                order.getUser().getId(), order.getUser().getName(), order.getUser().getEmail()
        ));
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public List<OrderModel> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    public List<OrderModel> getOrderByUser(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(this::map)
                .toList();
    }
}
