package ru.vorobev.spring_boot_library.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vorobev.spring_boot_library.models.Book;
import ru.vorobev.spring_boot_library.servises.BooksServices;

@RestController
@RequestMapping("/books")
public class BooksController {
    private BooksServices bookService;

    public BooksController(BooksServices bookService) {
        this.bookService = bookService;
    }

    public BooksServices getBookService() {
        return bookService;
    }

    public void setBookService(BooksServices bookService) {
        this.bookService = bookService;
    }

    private final static Logger logger = LoggerFactory.getLogger(BooksController.class);

    @GetMapping
    public ResponseEntity<?> getBooks() {
        System.out.println("ResponseEntity books");
        try {
            return ResponseEntity.ok().body(bookService.findAllBooks());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error books not found");
        }
    }
    //TODO убрать трай и кэтч
    @GetMapping(value = "/book/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBook(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok().body(bookService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error books not found");
        }
    }
    //TODO убрать трай и кэтч
    @PostMapping()
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        logger.info("Book " + book);
        try {
            logger.info("try " + book);
            return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error book not added");
        }
    }
//TODO убрать трай и кэтч
    // not work
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Integer id, @RequestBody Book book) {
        try {
            return new ResponseEntity<>(bookService.updateBook(book, id), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Book not updated");
        }
    }
    //TODO убрать трай и кэтч
    //work
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer id) {
        try {
            bookService.deleteBookById(id);
            return ResponseEntity.ok().body("Book was deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Book not deleted");
        }
    }

    @GetMapping("/isTaken")
    public ResponseEntity<?> bookIsTaken() {
        return new ResponseEntity<>(bookService.bookIsTaken(), HttpStatus.FOUND);
    }
}
