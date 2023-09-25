package com.ideas.pizzeria.handler.exception;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String message) { //Con el constructor le paso el mensaje
        super(message);
    }
}
