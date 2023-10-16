package ru.vorobev.spring_boot_library.servises;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vorobev.spring_boot_library.exeptions.NotFoundException;
import ru.vorobev.spring_boot_library.models.Book;
import ru.vorobev.spring_boot_library.repositories.BookRepository;

@Slf4j
@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book findById(Integer id) {
        System.out.println(bookRepository.findById(id));
        System.out.println("findById");
        return bookRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Book " + id + " not found "));
    }

    @Transactional
    public Book updateBook(Book book, Integer id) {
        log.info("updateBook " + book.toString());
        Book updatedBook = bookRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Book " + id + " not found "));
        updatedBook.setAuthor(book.getAuthor());
        updatedBook.setTimeBookTaking(book.getTimeBookTaking());
        updatedBook.setBookIsTaken(book.getBookIsTaken());
        updatedBook.setTitle(book.getTitle());
        updatedBook.setYearOfPublication(book.getYearOfPublication());
        updatedBook.setPerson(book.getPerson());
        return bookRepository.save(updatedBook);

    }
 //   public B

    public void deleteBookById(Integer id) {
        if (bookRepository.findById(id).isEmpty())
            throw new NotFoundException("Book " + id + " not found ");
        bookRepository.deleteById(id);
    }

}
