package com.ideas.pizzeria.service;

import com.ideas.pizzeria.dto.OrderDto;
import com.ideas.pizzeria.dto.ProductDto;
import com.ideas.pizzeria.handler.exception.ResourceNotFoundException;

import java.util.List;

public interface IOrderService {

    OrderDto createOrder(Long userId, List<ProductDto> productDtos) throws ResourceNotFoundException;

    OrderDto findOrderById(Long orderId) throws ResourceNotFoundException;
    void updateOrder(OrderDto orderDto) throws ResourceNotFoundException;
    void deleteOrder(Long orderId) throws ResourceNotFoundException;
    List<OrderDto> listOrders();
}
