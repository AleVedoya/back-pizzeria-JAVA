package com.ideas.pizzeria.repository;

import com.ideas.pizzeria.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
