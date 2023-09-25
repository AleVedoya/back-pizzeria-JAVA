package com.ideas.pizzeria.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideas.pizzeria.dto.OrderDto;
import com.ideas.pizzeria.dto.ProductDto;
import com.ideas.pizzeria.handler.exception.ResourceNotFoundException;
import com.ideas.pizzeria.model.Order;
import com.ideas.pizzeria.model.Product;
import com.ideas.pizzeria.model.User;
import com.ideas.pizzeria.repository.OrderRepository;
import com.ideas.pizzeria.repository.ProductRepository;
import com.ideas.pizzeria.repository.UserRepository;
import com.ideas.pizzeria.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ObjectMapper mapper;

    @Override
    public OrderDto createOrder(Long userId, List<ProductDto> productDtos) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Product> productList = productDtos
                .stream()
                .map(productDto -> {
                    try {
                        return productRepository.findById(productDto.getId())
                                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
                    } catch (ResourceNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toList();

        Order newOrder = new Order();
        newOrder.setUser(user);
        newOrder.setProducts(new HashSet<>(productList));

        orderRepository.save(newOrder);
        return mapper.convertValue(newOrder, OrderDto.class);
    }

    @Override
    public OrderDto findOrderById(Long orderId) throws ResourceNotFoundException {
        Order orderToFind = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return mapper.convertValue(orderToFind, OrderDto.class);
    }

    @Override
    public void updateOrder(OrderDto orderDto) throws ResourceNotFoundException {
        Order orderToFind = orderRepository.findById(orderDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        orderRepository.save(orderToFind);
    }

    @Override
    public void deleteOrder(Long orderId) throws ResourceNotFoundException {
        Order orderToDelete = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        orderRepository.delete(orderToDelete);
    }

    @Override
    public List<OrderDto> listOrders() {
        List<Order> orders = orderRepository.findAll();

        List<OrderDto> orderDtoList = orders
                .stream()
                .map(order -> mapper.convertValue(order, OrderDto.class))
                .collect(Collectors.toList());
        return orderDtoList;
    }
}
