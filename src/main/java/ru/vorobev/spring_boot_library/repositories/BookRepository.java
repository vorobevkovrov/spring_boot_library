package ru.vorobev.spring_boot_library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.vorobev.spring_boot_library.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findBooksByBookIsTakenTrue();
    @Query(nativeQuery = true, value = "select * from books where book_is_taken=true")
    List<Book> findBooksIsTaken();
    Optional<Book> findById(Integer id);
    @Query(nativeQuery = true, value = "select person_id from books where person_id is not null")
    ArrayList<Integer> findPersonIdFromBooks();
    List<Book> findBooksByBookIsTakenFalse();
    @Query(nativeQuery = true, value = "select book_is_taken from books where book_is_taken=true")
    List<Book> findBooksByBookIsTakenTrueAndPerson();
}
