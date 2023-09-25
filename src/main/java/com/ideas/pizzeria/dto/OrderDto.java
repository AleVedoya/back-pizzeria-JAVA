package com.ideas.pizzeria.dto;

import com.ideas.pizzeria.model.Product;
import com.ideas.pizzeria.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    Long id;
    User user;
    Set<Product> products = new HashSet<>();
}
