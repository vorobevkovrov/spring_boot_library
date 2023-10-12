package ru.vorobev.spring_boot_library.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private String message;
    private String debugMessage;
    private LocalDateTime timestamp;
}
