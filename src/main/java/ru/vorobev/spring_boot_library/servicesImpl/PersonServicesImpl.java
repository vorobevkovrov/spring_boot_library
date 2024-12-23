package ru.vorobev.spring_boot_library.servicesImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vorobev.spring_boot_library.models.Person;
import ru.vorobev.spring_boot_library.repositories.PeopleRepository;
import ru.vorobev.spring_boot_library.services.PeopleService;

import java.util.List;

@Service
public class PersonServicesImpl implements PeopleService {
    final private PeopleRepository peopleRepository;
    private final static Logger logger = LoggerFactory.getLogger(PersonServicesImpl.class);

    public PersonServicesImpl(PeopleRepository peopleRepository) {
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

    //TODO make this method
    public List<Person> findOnlyPeoples() {
        return peopleRepository.findOnlyPeoples();
    }

    @Transactional
    public Person addPerson(Person person) {
        logger.info("addPerson PersonServicesImpl");
        return peopleRepository.save(person);
    }
}
