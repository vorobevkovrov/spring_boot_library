package ru.vorobev.spring_boot_library.servises;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vorobev.spring_boot_library.exeptions.NotFoundException;
import ru.vorobev.spring_boot_library.exeptions.ResourceNotFoundExceptions;
import ru.vorobev.spring_boot_library.models.Book;
import ru.vorobev.spring_boot_library.repositories.BookRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class BooksServices {
    private BookRepository bookRepository;

    public BooksServices(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        for (Book book : bookList) {
            System.out.println(book);
        }
        return bookRepository.findAll();
    }

    public Book findById(Integer id) {
        System.out.println(bookRepository.findById(id));
        System.out.println("findById");
        return bookRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Книга не найдена "+new Timestamp(System.currentTimeMillis())));
    }

    @Transactional
    public Book updateBook(Book book, Integer id) {
        Book updatedBook = bookRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Книга не найдена"));
        updatedBook.setAuthor(book.getAuthor());
        updatedBook.setBookIsTaken(book.getBookIsTaken());
        updatedBook.setTitle(book.getTitle());
        updatedBook.setYearOfPublication(book.getYearOfPublication());
        updatedBook.setPerson(book.getPerson());
        bookRepository.save(updatedBook);
        return updatedBook;
    }

    public List<Book> bookIsTaken() {
        return bookRepository.findBooksByBookIsTakenTrue();
    }

    public void deleteBookById(Integer id) {
        bookRepository.deleteById(id);
    }
}
