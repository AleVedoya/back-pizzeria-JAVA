package com.ideas.pizzeria.handler.exception;

import com.ideas.pizzeria.handler.ErrorDetail;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.ideas.pizzeria.handler.ResponseBuilder.responseBuilder;
import static org.springframework.http.HttpStatus.*;

public class GlobalExceptions {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({ChangeSetPersister.NotFoundException.class,
            ResourceNotFoundException.class
    })
    public ResponseEntity<?> notFoundHandler(HttpServletRequest request, Exception exception) {
        return responseBuilder(NOT_FOUND, request.getRequestURI(), new ErrorDetail(exception));
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler({ResourceAlreadyExistsException.class})
    public ResponseEntity<?> alreadyExistsHandler(HttpServletRequest request, Exception exception) {
        return responseBuilder(CONFLICT, request.getRequestURI(), new ErrorDetail(exception));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({ArithmeticException.class,
            MissingRequestHeaderException.class,
            MethodArgumentNotValidException.class,
            NullPointerException.class,
            IllegalArgumentException.class,
            IndexOutOfBoundsException.class,
    })
    public ResponseEntity<?> badRequestHandler(HttpServletRequest request, Exception exception) {
        return responseBuilder(BAD_REQUEST, request.getRequestURI(), new ErrorDetail(exception));
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> internalServerErrorHandler(HttpServletRequest request, Exception exception) {
        return responseBuilder(INTERNAL_SERVER_ERROR, request.getRequestURI(), new ErrorDetail(exception));
    }
}
