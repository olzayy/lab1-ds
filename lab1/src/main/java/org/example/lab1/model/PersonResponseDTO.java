package org.example.lab1.model;

import jakarta.validation.constraints.NotNull;
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
public class PersonResponseDTO implements Serializable {

    @NotNull
    private Integer id;

    @NotNull
    private String name;

    private Integer age;

    private String address;

    private String work;
}
