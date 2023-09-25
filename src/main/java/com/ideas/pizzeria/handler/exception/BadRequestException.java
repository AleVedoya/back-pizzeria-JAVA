package com.ideas.pizzeria.handler.exception;

public class BadRequestException extends Exception{
    public BadRequestException(String message) {
        super(message);
    }

}