package com.ideas.pizzeria.repository;

import com.ideas.pizzeria.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
