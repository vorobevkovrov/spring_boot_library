package ru.vorobev.spring_boot_library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vorobev.spring_boot_library.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findBooksByBookIsTakenTrue();
    List<Book> findBooksByBookIsTakenFalse();
//   @Query(nativeQuery = true, value = "select book_is_taken from books where book_is_taken=true")
//   List<Book>  findBooksByBookIsTakenTrueAndPerson();
}
