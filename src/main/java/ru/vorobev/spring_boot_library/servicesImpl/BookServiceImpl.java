package ru.vorobev.spring_boot_library.servicesImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vorobev.spring_boot_library.exceptions.IsTakenException;
import ru.vorobev.spring_boot_library.exceptions.NotFoundException;
import ru.vorobev.spring_boot_library.models.Book;
import ru.vorobev.spring_boot_library.models.Person;
import ru.vorobev.spring_boot_library.repositories.BookRepository;
import ru.vorobev.spring_boot_library.repositories.PeopleRepository;
import ru.vorobev.spring_boot_library.services.BookService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private PeopleRepository peopleRepository;

    public BookServiceImpl(BookRepository bookRepository, PeopleRepository peopleRepository) {
        this.bookRepository = bookRepository;
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    //    public Book findById(Integer id) {
//        return bookRepository.findById(id).orElseThrow(() ->
//                new NotFoundException("Book " + id + " not found "));
//    }

    public Optional<Book> findById(Integer id) {
        return Optional.ofNullable(bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book " + id + " not found ")));
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

    public void deleteBookById(Integer id) {
//        bookRepository.findById(id).ifPresentOrElse(book->deleteBookById(id),
//                () -> {throw new NotFoundException("Could not delete book " + id + " because book not found ");});

        bookRepository.findById(id).ifPresentOrElse(
                 book -> bookRepository.deleteById(id),
                () -> {
                    throw new NotFoundException("Could not delete book " + id + " because book not found ");
                });

//        if (bookRepository.findById(id).isEmpty())
//            throw new NotFoundException("Could not delete book " + id + " because book not found ");
//        bookRepository.deleteById(id);
    }

    public void addBookToPerson(int personId, int bookId) {
        log.info("addBookToPerson");
        if (bookRepository.findBooksByBookIsTakenTrue().isEmpty()) {
            Book book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Book " + bookId + " not found "));
            Person person = peopleRepository.findById(personId).orElseThrow();
            book.setPerson(person);
            book.setBookIsTaken(true);
            bookRepository.save(book);
        } else throw new IsTakenException("Book is already taken");
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
    public List<Book> findBooksIsTaken() {
        log.info("BookServices findBooksIsTaken");
        return bookRepository.findBooksIsTaken();
    }

    public List<Book> findPersonIdFromBooks() {
        return findPersonIdFromBooks();
    }
}


