package ru.vorobev.spring_boot_library.dto;

import lombok.Data;
import ru.vorobev.spring_boot_library.models.Book;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PeopleDto {
    private Integer id;
    private int age;
    private String name;
    private String email;
    private List<Book> books = new ArrayList<>();
    private LocalDate dateOfBirth;
    private LocalDateTime createdAt;
}
