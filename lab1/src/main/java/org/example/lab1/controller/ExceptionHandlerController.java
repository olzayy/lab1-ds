package org.example.lab1.controller;

import org.example.lab1.model.ErrorResponseDTO;
import org.example.lab1.exception.ResourceNotFoundException;
import org.example.lab1.model.ValidationErrorResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Olga Zaitseva
 */
@RestControllerAdvice
public class ExceptionHandlerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);

    private Map<String, String> validationErrors(List<FieldError> errors) {
        Map<String, String> validationErrors = new HashMap<>();
        errors.forEach(fieldError -> validationErrors.put(
                fieldError.getField(),
                "Field has wrong value"
        ));
        return validationErrors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorResponseDTO badRequest(MethodArgumentNotValidException exception) {
        Map<String, String> errors = validationErrors(exception.getBindingResult().getFieldErrors());
        LOGGER.info("Bad request '{}'", errors);
        return new ValidationErrorResponseDTO("Invalid data", errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ValidationErrorResponseDTO badRequest() {
        Map<String, String> errors = new HashMap<>();
        errors.put("age", "Field has wrong value");
        return new ValidationErrorResponseDTO("Invalid data", errors);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponseDTO resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponseDTO message = new ErrorResponseDTO(ex.getMessage());
        LOGGER.warn("There are some troubles with finding resource: {}", message);
        return message;
    }
}
