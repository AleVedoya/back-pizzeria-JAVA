package com.ideas.pizzeria.service;

import com.ideas.pizzeria.dto.OrderDto;
import com.ideas.pizzeria.dto.UserDto;
import com.ideas.pizzeria.handler.exception.ResourceNotFoundException;
import com.ideas.pizzeria.model.User;

import java.util.List;

public interface IUserService {

    UserDto createUser(UserDto userDto);
    UserDto findUserById(Long userId) throws ResourceNotFoundException;
    void updateUser(UserDto userDto) throws ResourceNotFoundException;
    void deleteUser(Long userId) throws ResourceNotFoundException;
    List<UserDto> listUsers();
}
