package ru.vorobev.spring_boot_library.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookDto {
    private Integer Id;
    private String title;
    private String description;
    private String author;
    private int year_of_publication;
    private LocalDate time_book_is_taken;
    private Boolean book_is_taken;
    private List Person;
}
