package ru.vorobev.spring_boot_library.exeptions;

public class NotFoundException extends AppException {
    public NotFoundException(String message) {
        super(message);
    }
}
