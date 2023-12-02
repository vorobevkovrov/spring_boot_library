package ru.vorobev.spring_boot_library.services;

import ru.vorobev.spring_boot_library.models.Person;

import java.util.List;

public interface PeopleService {
     List<Person> findAllPeoplesWithBooks();
     List<Person> findOnlyPeoples();
     Person addPerson(Person person);
}
