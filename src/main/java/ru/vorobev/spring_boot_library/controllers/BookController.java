package ru.vorobev.spring_boot_library.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vorobev.spring_boot_library.models.Book;
import ru.vorobev.spring_boot_library.servises.BookService;
import ru.vorobev.spring_boot_library.servises.BooksServices;


@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {
    private BookService bookService;
    private BooksServices booksServices;

    public BookController(BookService bookService, BooksServices booksServices) {
        this.bookService = bookService;
        this.booksServices = booksServices;
    }

    // /book/id
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getBook(@PathVariable Integer id) {
        log.info("ResponseEntity<?> getBook (@PathVariable Integer id)");
        return ResponseEntity.ok().body(bookService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        log.info("Book " + book);
        return new ResponseEntity(bookService.addBook(book), HttpStatus.CREATED);
    }

    // /book/id
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Integer id, @RequestBody Book book) {
        log.info("updateBook" + book);
        return new ResponseEntity<>(bookService.updateBook(book, id), HttpStatus.OK);
    }

    //work
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>("Book was deleted", HttpStatus.OK);
    }

    // /book/isTaken
    // TODO get books with owners
    @GetMapping("/isTaken")
    public ResponseEntity<?> bookIsTaken() {
        return new ResponseEntity<>(booksServices.findBooksIsTaken(), HttpStatus.FOUND);
    }
}

