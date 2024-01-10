package com.example.gerenciadordetarefas.util.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestControllerAdviceHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public RespondMsg resourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        String error = "Not Found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        RespondMsg err1 = new RespondMsg(Instant.now(), status.value(),error, ex.getMessage(), request.getRequestURI());
        return err1;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public RespondMsg resourceNotFoundException(ResourceNotFoundException ex) {
//        String error = "Not Found";
//        HttpStatus status = HttpStatus.NOT_FOUND;
//        RespondMsg err1 = new RespondMsg(error, ex.getMessage());
//        return err1;
//    }
}
