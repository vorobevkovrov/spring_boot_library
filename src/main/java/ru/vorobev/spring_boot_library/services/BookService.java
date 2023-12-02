package ru.vorobev.spring_boot_library.services;

import ru.vorobev.spring_boot_library.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book addBook(Book book);

    Optional<Book> findById(Integer id);

    Book updateBook(Book book, Integer id);

    void deleteBookById(Integer id);

    void addBookToPerson(int personId, int bookId);

    List<Book> findAllBooks();

    List<Book> findBooksByBookIsTakenFalse();

    List<Book> findBooksIsTaken();

    List<Book> findPersonIdFromBooks();
}
