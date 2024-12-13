package org.example.lab1.service;

import org.example.lab1.entity.Person;
import org.example.lab1.model.PersonRequestDTO;
import org.example.lab1.exception.ResourceNotFoundException;
import org.example.lab1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для person
 *
 * @author Olga Zaitseva
 */

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    public Integer addPerson(PersonRequestDTO personRequestDTO) {
        /*if (personRequestDTO.getName() == null || personRequestDTO.getName().isEmpty()) {
            throw new ResourceNotValidException("Invalid data");
        }*/
        Person person = new Person();
        person.setName(personRequestDTO.getName());
        person.setAge(personRequestDTO.getAge());
        person.setAddress(personRequestDTO.getAddress());
        person.setWork(personRequestDTO.getWork());
        return personRepository.save(person).getId();
    }

    public Person findPersonById(Integer id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found person for ID"));
    }

    public void deletePersonById(Integer id) {
        personRepository.deleteById(id);
    }

    public Person updatePerson(Integer id, PersonRequestDTO personRequestDTO) {
        Person person = findPersonById(id);
        Optional.ofNullable(personRequestDTO.getName())
                .ifPresent(person::setName);
        Optional.ofNullable(personRequestDTO.getAge())
                .ifPresent(person::setAge);
        Optional.ofNullable(personRequestDTO.getAddress())
                .ifPresent(person::setAddress);
        Optional.ofNullable(personRequestDTO.getWork())
                .ifPresent(person::setWork);
        return personRepository.save(person);
    }
}
