package org.example.lab1;

import org.example.lab1.entity.Person;
import org.example.lab1.model.PersonRequestDTO;
import org.example.lab1.repository.PersonRepository;
import org.example.lab1.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.when;

/**
 * Тесты на PersonService
 *
 * @author Olga Zaitseva
 * @since 23.11.2024
 */
public class PersonServiceTest {
    @Mock
    PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    Person person;

    @Test
    void findByIdTest () {
        person = new Person(1, "Olga", 22, "Moscow", "BMSTU");
        when(personRepository.findById(1)).thenReturn(Optional.ofNullable(person));
        Person result = personService.findPersonById(1);
        Assertions.assertEquals(person, result);
    }

    @Test
    public void findAllPersonsTest() {
        List<Person> persons = Arrays.asList(new Person(1, "John", 30, "Engineer", "BMSTU"),
                new Person(2, "Jane", 25, "Doctor", "Hospital"));
        when(personRepository.findAll()).thenReturn(persons);
        List<Person> result = personService.findAllPersons();
        Assertions.assertEquals(persons, result);
    }

    @Test
    public void testEditPerson() {
        int personId = 1;
        Person person = new Person(1, "John", 30, "Engineer", "BMSTU");
        PersonRequestDTO personRequestDTO = new PersonRequestDTO();
        personRequestDTO.setAge(43);
        personRequestDTO.setName("May");
        Mockito.when(personRepository.findById(personId)).thenReturn(Optional.of(person));
        Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);

        Person result = personService.updatePerson(personId, personRequestDTO);

        Assertions.assertEquals("May", result.getName());
        Assertions.assertEquals(43, result.getAge());
    }

    @Test
    public void deletePersonTest() {
        int personId = 1;
        personService.deletePersonById(personId);
        Mockito.verify(personRepository, Mockito.times(1)).deleteById(personId);
    }
}
