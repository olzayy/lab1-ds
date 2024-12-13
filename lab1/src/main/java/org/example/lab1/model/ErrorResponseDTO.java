package org.example.lab1.model;

import lombok.Data;

/**
 * @author Olga Zaitseva
 */
@Data
public class ErrorResponseDTO {
    private String message;

    public ErrorResponseDTO(String message) {
        this.message = message;
    }
}
