package ru.vorobev.spring_boot_library.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vorobev.spring_boot_library.models.Book;
import ru.vorobev.spring_boot_library.servicesImpl.BookServiceImpl;


@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {
    final private BookServiceImpl bookServiceImpl;

    public BookController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    // /book/id
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getBook(@PathVariable Integer id) {
        log.info("ResponseEntity<?> getBook (@PathVariable Integer id)");
        return ResponseEntity.ok().body(bookServiceImpl.findById(id));
    }

    @PostMapping()
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        log.info("Book " + book);
        return new ResponseEntity<>(bookServiceImpl.addBook(book), HttpStatus.CREATED);
    }

    // /book/id
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Integer id, @RequestBody Book book) {
        log.info("updateBook" + book);
        return new ResponseEntity<>(bookServiceImpl.updateBook(book, id), HttpStatus.OK);
    }

    //work
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer id) {
        bookServiceImpl.deleteBookById(id);
        return new ResponseEntity<>("Book was deleted", HttpStatus.OK);
    }

    // /book/isTaken
    // TODO fix answer in json
    @GetMapping("/isTaken")
    public ResponseEntity<?> bookIsTaken() {
        return new ResponseEntity<>(bookServiceImpl.findBooksIsTaken(), HttpStatus.FOUND);
    }

    @PostMapping("/takeBook/{bookId}/{personId}")
    public ResponseEntity<?> takeBook(@PathVariable("bookId") int bookId, @PathVariable("personId") int personId) {
        log.info("@PostMapping(/takeBook/{bookId}/person)");
        bookServiceImpl.addBookToPerson(bookId, personId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getBooks() {
        log.info("getBooks() BooksController");
        return ResponseEntity.ok().body(bookServiceImpl.findAllBooks());

    }

    @GetMapping("/free")
    public ResponseEntity<?> getFreeBooks() {
        return new ResponseEntity<>(bookServiceImpl.findBooksByBookIsTakenFalse(), HttpStatus.FOUND);
    }
    //TODO get counts of free books
}



