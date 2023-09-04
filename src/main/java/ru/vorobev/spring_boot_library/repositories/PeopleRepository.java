package ru.vorobev.spring_boot_library.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.vorobev.spring_boot_library.models.Person;

public interface PeopleRepository extends JpaRepository<Person, Integer> {

}
