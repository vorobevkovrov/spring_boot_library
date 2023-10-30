package ru.vorobev.spring_boot_library.servises;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vorobev.spring_boot_library.exeptions.IsTakenException;
import ru.vorobev.spring_boot_library.exeptions.NotFoundException;
import ru.vorobev.spring_boot_library.models.Book;
import ru.vorobev.spring_boot_library.models.Person;
import ru.vorobev.spring_boot_library.repositories.BookRepository;
import ru.vorobev.spring_boot_library.repositories.PeopleRepository;

@Slf4j
@Service
public class BookService {
    private BookRepository bookRepository;
    private PeopleRepository peopleRepository;

    public BookService(BookRepository bookRepository, PeopleRepository peopleRepository) {
        this.bookRepository = bookRepository;
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book findById(Integer id) {
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

    public void addBookToPerson(int personId, int bookId) {
        log.info("addBookToPerson");
        if (bookRepository.findBooksByBookIsTakenTrue().isEmpty()) {
            Book book = bookRepository.findById(bookId).orElseThrow();
            Person person = peopleRepository.findById(personId).orElseThrow();
            book.setPerson(person);
            book.setBookIsTaken(true);
            bookRepository.save(book);
        } else throw new IsTakenException("Book is already taken");
    }
}
