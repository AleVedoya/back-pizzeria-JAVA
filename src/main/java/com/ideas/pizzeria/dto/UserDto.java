package com.ideas.pizzeria.dto;

import com.ideas.pizzeria.model.Order;
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
public class UserDto {

    Long id;
    String name;
    String email;
    String phone;
    String address;
    private Set<Order> orders = new HashSet<>();
}
