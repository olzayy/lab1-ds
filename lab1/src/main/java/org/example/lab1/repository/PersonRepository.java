package org.example.lab1.repository;

import org.example.lab1.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для person
 *
 * @author Olga Zaitseva
 * @since 22.11.2024
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
