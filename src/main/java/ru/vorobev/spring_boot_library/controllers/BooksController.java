package ru.vorobev.spring_boot_library.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vorobev.spring_boot_library.servises.BooksServices;

@Slf4j
@RestController
@RequestMapping("/books")
public class BooksController {
    private BooksServices booksServices;

    public BooksController(BooksServices booksServices) {
        this.booksServices = booksServices;
    }

    public BooksServices getBookService() {
        return booksServices;
    }

    public void setBookService(BooksServices booksServices) {
        this.booksServices = booksServices;
    }

    // private final static Logger logger = LoggerFactory.getLogger(BooksController.class);
//TODO remove try catch
    @GetMapping
    public ResponseEntity<?> getBooks() {
        log.info("ResponseEntity<?> getBooks()");
        try {
            return ResponseEntity.ok().body(booksServices.findAllBooks());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error books not found");
        }
    }
    //TODO get counts of free books
    //TODO get all free books
}
