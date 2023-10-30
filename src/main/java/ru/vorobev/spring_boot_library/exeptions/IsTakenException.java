package ru.vorobev.spring_boot_library.exeptions;

public class IsTakenException extends AppException{
    public IsTakenException(String message) {
        super(message);
    }
}
