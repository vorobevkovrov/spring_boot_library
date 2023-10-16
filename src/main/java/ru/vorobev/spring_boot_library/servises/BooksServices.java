package ru.vorobev.spring_boot_library.servises;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vorobev.spring_boot_library.exeptions.NotFoundException;
import ru.vorobev.spring_boot_library.models.Book;
import ru.vorobev.spring_boot_library.repositories.BookRepository;

import java.util.List;

@Slf4j
@Service
public class BooksServices {
    private BookRepository bookRepository;

    public BooksServices(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }


    public List<?> findBooksIsTaken() {
//        HashMap<Book, Person> bookList = new HashMap<>();

        if (bookRepository.findBooksByBookIsTakenTrue().isEmpty())
            throw new NotFoundException("Book is already taken not found ");
//        bookList.addAll(bookRepository.findBooksByBookIsTakenTrue());
        // Book book = new Book();
//        for (Map.Entry<Book, Person> bookPersonEntry : bookList.entrySet()) {        }
        return bookRepository.findBooksByBookIsTakenTrue();
    }
}
