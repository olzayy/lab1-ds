package org.example.lab1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Бин человека
 *
 * @author Olga Zaitseva
 */
@Entity
@Table(name="person")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String ADDRESS = "address";
    public static final String WORK = "work";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Integer id;

    @NotNull
    @Column(name = NAME)
    private String name;

    @Column(name = AGE)
    private Integer age;

    @Column(name = ADDRESS)
    private String address;

    @Column(name = WORK)
    private String work;
}
