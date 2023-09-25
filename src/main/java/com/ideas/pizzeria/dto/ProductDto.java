package com.ideas.pizzeria.dto;

import com.ideas.pizzeria.model.Order;
import com.ideas.pizzeria.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    Long id;
    String name;
    String description;
    double price;
    int quantity;
    String image;
    Category category;
    Order order;
}
