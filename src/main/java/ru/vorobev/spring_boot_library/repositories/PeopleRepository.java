package ru.vorobev.spring_boot_library.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.vorobev.spring_boot_library.models.Person;

import java.util.List;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM person")
    List<Person> findOnlyPeoples();
}
