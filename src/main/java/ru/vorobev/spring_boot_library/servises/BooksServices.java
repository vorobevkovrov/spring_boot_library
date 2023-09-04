package ru.vorobev.spring_boot_library.servises;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vorobev.spring_boot_library.exeptions.ResourceNotFoundExceptions;
import ru.vorobev.spring_boot_library.models.Book;
import ru.vorobev.spring_boot_library.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

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
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExceptions("Book", "Id", id));
    }

    public Book updateBook(Book book,Integer id) {
       Book book1 =  bookRepository.findById(id).orElseThrow(()->new ResourceNotFoundExceptions("Book", "Id", id));
        return bookRepository.save(book1);
    }

    public void deleteBookById(Integer id) {
        bookRepository.deleteById(id);
    }
}
