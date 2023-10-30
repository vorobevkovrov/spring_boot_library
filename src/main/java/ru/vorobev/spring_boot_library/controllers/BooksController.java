package ru.vorobev.spring_boot_library.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    @GetMapping
    public ResponseEntity<?> getBooks() {
        log.info("getBooks() BooksController");
        return ResponseEntity.ok().body(booksServices.findAllBooks());

    }

    @GetMapping("/free")
    public ResponseEntity<?> getFreeBooks() {
        return new ResponseEntity<>(booksServices.findBooksByBookIsTakenFalse(), HttpStatus.FOUND) ;
    }
    //TODO get counts of free books
}
