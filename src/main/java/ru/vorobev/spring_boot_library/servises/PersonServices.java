package ru.vorobev.spring_boot_library.servises;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vorobev.spring_boot_library.models.Book;
import ru.vorobev.spring_boot_library.models.Person;
import ru.vorobev.spring_boot_library.repositories.PeopleRepository;


import java.util.List;

@Service
public class PersonServices {
    private PeopleRepository peopleRepository;
    private final static Logger logger = LoggerFactory.getLogger(PersonServices.class);

    public PersonServices(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAllPeoplesWithBooks() {
        logger.info("findAllPeoples");
        List<Person> personList = peopleRepository.findAll();
        for (Person person : personList) {
            System.out.println(person);
        }
        return peopleRepository.findAll();
    }

    public List<Person> findOnlyPeoples() {

        return null;
    }

    @Transactional
    public Person addPerson(Person person) {
        logger.info("addPerson PersonServices");
        return peopleRepository.save(person);
    }
}
