package com.backend.spring.JPA.service;

import com.backend.spring.JPA.entities.Orders;
import com.backend.spring.JPA.entities.Users;
import com.backend.spring.JPA.model.CreateOrderModel;
import com.backend.spring.JPA.model.OrderModel;
import com.backend.spring.JPA.model.UserModel;
import com.backend.spring.JPA.repository.OrderRepository;
import com.backend.spring.JPA.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Transactional
    public OrderModel createOrders(CreateOrderModel createOrderModel, Long id) {
        Users user = userRepository.findById(id).orElseThrow();
        Orders order = new Orders();
        order.setUser(user);
        order.setProductName(createOrderModel.getProductName());

        Orders savedOrder = orderRepository.save(order);
        return new OrderModel(savedOrder.getId(), savedOrder.getProductName(),
                new UserModel(savedOrder.getUser().getId(), savedOrder.getUser().getName(), savedOrder.getUser().getEmail()));
    }

    public List<OrderModel> getOrdersById(Long id) {
        List<Orders> ordersList = orderRepository.findByUserId(id);
        List<OrderModel> orderModels = new ArrayList<>();
        ordersList.forEach(orders -> {
            OrderModel orderModel = new OrderModel(orders.getId(), orders.getProductName(),
                    new UserModel(orders.getUser().getId(), orders.getUser().getName(), orders.getUser().getEmail()));
            orderModels.add(orderModel);
        });

        return orderModels;
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

    public boolean deleteOrder(Long userId, Long orderId) {
        Orders order = orderRepository.findById(orderId).orElseThrow();
        if (!order.getUser().getId().equals(userId)) {
            return false;
        }

        orderRepository.delete(order);
        return true;
    }
}
