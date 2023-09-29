package ru.vorobev.spring_boot_library.exeptions;

public class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }
}
