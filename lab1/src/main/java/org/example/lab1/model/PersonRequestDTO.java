package org.example.lab1.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Olga Zaitseva
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequestDTO implements Serializable {

    @NotBlank
    private String name;

    @Positive
    private Integer age;

    private String address;

    private String work;
}
