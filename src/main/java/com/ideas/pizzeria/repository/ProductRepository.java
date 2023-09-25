package com.ideas.pizzeria.repository;

import com.ideas.pizzeria.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
