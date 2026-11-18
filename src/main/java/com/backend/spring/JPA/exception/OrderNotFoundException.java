package com.backend.spring.JPA.exception;

public class OrderNotFoundException extends ResourceNotFoundException {

    public OrderNotFoundException(Long id) {
        super(ErrorCode.ORDER_NOT_FOUND, "Order not found with id : " + id);
    }
}
