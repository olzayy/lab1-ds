package org.example.lab1.controller;

import jakarta.validation.Valid;
import org.example.lab1.entity.Person;
import org.example.lab1.model.PersonRequestDTO;
import org.example.lab1.model.PersonResponseDTO;
import org.example.lab1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер для обработки запросов /persons
 *
 * @author Olga Zaitseva
 * @since 22.11.2024
 */
@RestController
@RequestMapping("api/v1/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping()
    public ResponseEntity<?> listPersons() {
        return ResponseEntity.ok(convertPersonListToDTO(personService.findAllPersons()));
    }

    @PostMapping()
    public ResponseEntity<?> createPerson(@Valid @RequestBody PersonRequestDTO personRequestDTO) {
        Integer personId = personService.addPerson(personRequestDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(personId)
                .toUri()).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPerson(@PathVariable Integer id) {
        return ResponseEntity.ok(convertPersonToDTO(personService.findPersonById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Integer id) {
        personService.deletePersonById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable Integer id, @RequestBody PersonRequestDTO personRequestDTO) {
        return ResponseEntity.ok(convertPersonToDTO(personService.updatePerson(id, personRequestDTO)));
    }

    public List<PersonResponseDTO> convertPersonListToDTO(List<Person> personList) {
        List<PersonResponseDTO> personResponseDTOList = new ArrayList<>();
        personList.forEach(person -> {
            personResponseDTOList.add(convertPersonToDTO(person));
        });
        return personResponseDTOList;
    }

    public PersonResponseDTO convertPersonToDTO(Person person) {
        PersonResponseDTO personResponseDTO = new PersonResponseDTO();
        personResponseDTO.setId(person.getId());
        personResponseDTO.setName(person.getName());
        personResponseDTO.setAge(person.getAge());
        personResponseDTO.setAddress(person.getAddress());
        personResponseDTO.setWork(person.getWork());
        return personResponseDTO;
    }
}
