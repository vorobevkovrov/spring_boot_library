package ru.vorobev.spring_boot_library.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "year_of_publication")
    private int yearOfPublication;

    @ManyToOne
    @JoinColumn(name = "person_id")
 //   @JsonManagedReference
    private Person person;

    @Column(name = "time_book_is_taken")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate timeBookTaking;

    @Column(name = "book_is_taken")
    private Boolean bookIsTaken;
    @Column
    private String description;

    public Book() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDate getTimeBookTaking() {
        return timeBookTaking;
    }

    public void setTimeBookTaking(LocalDate timeBookTaking) {
        this.timeBookTaking = timeBookTaking;
    }

    public Boolean getBookIsTaken() {
        return bookIsTaken;
    }

    public void setBookIsTaken(Boolean bookIsTaken) {
        this.bookIsTaken = bookIsTaken;
    }

    @Override
    public String toString() {
        return this.title + " " + this.author;
    }
}
