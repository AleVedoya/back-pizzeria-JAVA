package com.ideas.pizzeria.controller;

import com.ideas.pizzeria.dto.UserDto;
import com.ideas.pizzeria.handler.exception.ResourceNotFoundException;
import com.ideas.pizzeria.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ideas.pizzeria.handler.ResponseBuilder.responseBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long userId) throws ResourceNotFoundException {
        return responseBuilder(HttpStatus.OK, userService.findUserById(userId));
    }

    @PutMapping("/update")
    public ResponseEntity<?> modifyUser(@RequestBody UserDto userDto) throws ResourceNotFoundException {
        userService.updateUser(userDto);
        return responseBuilder(HttpStatus.OK, userDto);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Long userId) throws ResourceNotFoundException {
        userService.deleteUser(userId);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllUsers() {
        return responseBuilder(HttpStatus.OK, userService.listUsers());
    }
}
