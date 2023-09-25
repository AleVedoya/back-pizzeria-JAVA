package com.ideas.pizzeria.controller;

import com.ideas.pizzeria.dto.OrderDto;
import com.ideas.pizzeria.dto.ProductDto;
import com.ideas.pizzeria.handler.exception.ResourceNotFoundException;
import com.ideas.pizzeria.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ideas.pizzeria.handler.ResponseBuilder.responseBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderServiceImpl orderService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestParam("user") Long userId,
                                         @RequestBody List<ProductDto> productDtos) {
        try {
            OrderDto newOrder = orderService.createOrder(userId, productDtos);
            return responseBuilder(HttpStatus.CREATED, newOrder);
        } catch (ResourceNotFoundException ex) {
            return responseBuilder(HttpStatus.NOT_FOUND, "User or product not found");
        }
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable("id") Long orderId) throws ResourceNotFoundException {
        return responseBuilder(HttpStatus.OK, orderService.findOrderById(orderId));
    }

    @PutMapping("/update")
    public ResponseEntity<?> modifyOrder(@RequestBody OrderDto orderDto) throws ResourceNotFoundException {
        orderService.updateOrder(orderDto);
        return responseBuilder(HttpStatus.OK, orderDto);
    }

    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable("id") Long orderId) throws ResourceNotFoundException {
        orderService.deleteOrder(orderId);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllOrders() {
        return responseBuilder(HttpStatus.OK, orderService.listOrders());
    }
}
