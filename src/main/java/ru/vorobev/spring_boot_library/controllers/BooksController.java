package ru.vorobev.spring_boot_library.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vorobev.spring_boot_library.models.Book;
import ru.vorobev.spring_boot_library.servises.BooksServices;
@Slf4j
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

   // private final static Logger logger = LoggerFactory.getLogger(BooksController.class);

    @GetMapping
    public ResponseEntity<?> getBooks() {
        log.info("ResponseEntity<?> getBooks()");
        try {
            return ResponseEntity.ok().body(bookService.findAllBooks());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error books not found");
        }
    }

    //TODO убрать трай и кэтч
    @PostMapping()
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        log.info("Book " + book);
        try {
            log.info("try " + book);
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
