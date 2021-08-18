package com.kazakova.mylibrarywithrest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class AwesomeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<AwesomeException> handleThereIsNoSuchObjectException() {
        return new ResponseEntity<>(new AwesomeException(), HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    private static class AwesomeException {
        private String message;

        public AwesomeException() {

        }
    }

}
