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
}
