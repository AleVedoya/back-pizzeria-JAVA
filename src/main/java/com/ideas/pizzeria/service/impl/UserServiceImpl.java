package com.ideas.pizzeria.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideas.pizzeria.dto.UserDto;
import com.ideas.pizzeria.handler.exception.ResourceNotFoundException;
import com.ideas.pizzeria.model.User;
import com.ideas.pizzeria.repository.UserRepository;
import com.ideas.pizzeria.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final ObjectMapper mapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User newUser = mapper.convertValue(userDto, User.class);
        userRepository.save(newUser);
        return mapper.convertValue(newUser, UserDto.class);
    }

    @Override
    public UserDto findUserById(Long userId) throws ResourceNotFoundException {
        User userToFind = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return mapper.convertValue(userToFind, UserDto.class);
    }

    @Override
    public void updateUser(UserDto userDto) throws ResourceNotFoundException {
        User userToFind = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        mapper.convertValue(userRepository.save(userToFind), UserDto.class);
    }

    @Override
    public void deleteUser(Long userId) throws ResourceNotFoundException {
        User userToDelete = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(userToDelete);
    }

    @Override
    public List<UserDto> listUsers() {
        List<User> users = userRepository.findAll();

        List<UserDto> userDtoList = users
                .stream()
                .map(user -> mapper.convertValue(user, UserDto.class))
                .collect(Collectors.toList());
        return userDtoList;
    }
}
