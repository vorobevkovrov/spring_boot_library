package ru.vorobev.spring_boot_library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.vorobev.spring_boot_library.models.ErrorMessage;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> notFoundException(NotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage("Resource not found ", exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IsTakenException.class)
    public ResponseEntity<ErrorMessage> isTakenException(IsTakenException isTakenException){
        ErrorMessage errorMessage = new ErrorMessage("Book is already taken",isTakenException.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(errorMessage,HttpStatus.IM_USED);
    }
}
