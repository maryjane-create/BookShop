package com.example.bookstore.exception;

import com.example.bookstore.util.MethodUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookCustomExceptionHandler {

    @ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity<String> applicationException(ApplicationException exception){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return  new ResponseEntity<>(MethodUtils.prepareErrorJSON(status, exception), status);
    }

    public  ResponseEntity<String> bookNotFoundException(BookNotFoundException exception){
        HttpStatus status = HttpStatus.NOT_FOUND;
        return  new ResponseEntity<>(MethodUtils.prepareErrorJSON(status, exception), status);
    }


}
