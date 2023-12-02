package ru.vorobev.spring_boot_library.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vorobev.spring_boot_library.models.Person;
import ru.vorobev.spring_boot_library.servicesImpl.PersonServicesImpl;

@Slf4j
@RestController
@RequestMapping("/peoples")
public class PeopleController {
   final private PersonServicesImpl personServices;

    public PeopleController(PersonServicesImpl personServices) {
        this.personServices = personServices;
    }
//TODO make getAllPeoples without books
    @GetMapping
    public ResponseEntity<?> getAllPeoples() {
        return ResponseEntity.ok().body(personServices.findAllPeoplesWithBooks());
    }
    @GetMapping("/without_books")
    public ResponseEntity<?> getAllPeoplesWithoutBooks(){
        return new ResponseEntity<>(personServices.findOnlyPeoples(),HttpStatus.FOUND);
    }

    @PostMapping()
    public ResponseEntity<?> addPerson(@RequestBody Person person) {
        log.info("addPerson people controller");
        personServices.addPerson(person);
        return new ResponseEntity<>(personServices.addPerson(person), HttpStatus.CREATED);
    }
}

