package ru.vorobev.spring_boot_library.exceptions;

public class AppException extends RuntimeException {
    private String message;

    public AppException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
