package ru.vorobev.spring_boot_library.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vorobev.spring_boot_library.exeptions.NotFoundException;
import ru.vorobev.spring_boot_library.models.ErrorMessage;
import ru.vorobev.spring_boot_library.servises.BooksServices;


@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {
    private BooksServices bookService;

    public BookController(BooksServices bookService) {
        this.bookService = bookService;
    }

    //TODO убрать трай и кэтч
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getBook(@PathVariable Integer id) {
//        log.info("ResponseEntity<?> getBook (@PathVariable Integer id)");
//        try {
//            log.info("try");
//            return ResponseEntity.ok().body(bookService.findById(id));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Error book not found");
//        }
        return ResponseEntity.ok().body(bookService.findById(id));
    }
}
