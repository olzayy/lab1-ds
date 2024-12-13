package org.example.lab1.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Olga Zaitseva
 */

@Setter
@Getter
public class ValidationErrorResponseDTO {
    private String message;
    private Map<String, String> errors;

    public ValidationErrorResponseDTO(String message, Map<String, String> errors) {
        this.message = message;
        this.errors = errors;
    }
}
