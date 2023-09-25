package com.ideas.pizzeria.handler.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(String message){
        super(message);
    }
}
