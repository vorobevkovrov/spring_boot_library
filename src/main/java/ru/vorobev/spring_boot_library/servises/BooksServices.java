package ru.vorobev.spring_boot_library.servises;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vorobev.spring_boot_library.exeptions.NotFoundException;
import ru.vorobev.spring_boot_library.models.Book;
import ru.vorobev.spring_boot_library.models.Person;
import ru.vorobev.spring_boot_library.repositories.BookRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class BooksServices {
    private BookRepository bookRepository;

    public BooksServices(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> findAllBooks() {
        if (bookRepository.findAll().isEmpty())
            throw new NotFoundException("Not books in database");
        return bookRepository.findAll();
    }

    public List<Book> findBooksByBookIsTakenFalse() {
        return bookRepository.findBooksByBookIsTakenFalse();
    }

    //TODO добавить в список людей у кого сейчас книга
//    public List<?> findBooksIsTaken() {
//        HashMap<Book, Person> bookList = new HashMap<>();
//
//        if (bookRepository.findBooksByBookIsTakenTrue().isEmpty())
//            throw new NotFoundException("Book is already taken not found ");
//       bookList.addAll(bookRepository.findBooksByBookIsTakenTrue());
//        // Book book = new Book();
//        for (Map.Entry<Book, Person> bookPersonEntry : bookList.entrySet()) {        }
//       return bookRepository.findBooksByBookIsTakenTrue();
//        return bookRepository.findBooksByBookIsTakenTrueAndPerson();
//    }
    public Map<Book, Person> findBooksIsTaken() {
        log.info("BooksServices findBooksIsTaken");
        HashMap<Book, Person> bookList = new HashMap<>();
        if (bookRepository.findBooksByBookIsTakenTrue().isEmpty())
            throw new NotFoundException("Book is already taken not found ");
        List<Book> bookListIsTaken = bookRepository.findBooksByBookIsTakenTrue();
        // Book book = new Book();
        for (Book book : bookListIsTaken) {
            bookList.put(book, book.getPerson());
            log.info(String.valueOf(bookList));
        }
        return bookList;
    }
}
