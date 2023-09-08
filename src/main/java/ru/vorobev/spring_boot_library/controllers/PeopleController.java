package ru.vorobev.spring_boot_library.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vorobev.spring_boot_library.models.Person;
import ru.vorobev.spring_boot_library.servises.PersonServices;
import org.slf4j.Logger;


@RestController
@RequestMapping("/peoples")
public class PeopleController {
    private PersonServices personServices;
    private final static Logger logger = LoggerFactory.getLogger(Person.class);

    public PeopleController(PersonServices personServices) {
        this.personServices = personServices;
    }

    @GetMapping
    public ResponseEntity<?> getAllPeoples() {
        return ResponseEntity.ok().body(personServices.findAllPeoplesWithBooks());
    }

    //TODO убрать трай и кэтч
    @PostMapping()
    public ResponseEntity<?> addPerson(@RequestBody Person person) {
        logger.info("addPerson " + person);
        try {
            personServices.addPerson(person);
            logger.info("try " + person.getName(), person.getAge(), person.getEmail(), person.getDateOfBirth(), person.getCreatedAt());
            return new ResponseEntity<>(personServices.addPerson(person), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error person not added");
        }
    }

}
