package com.andy.quizengine.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<CustomErrorMessage> handleQuizNotFound(
            QuizNotFoundException e, WebRequest request) {

        CustomErrorMessage body = new CustomErrorMessage(HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                e.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserWithEmailAlreadyExistsException.class)
    public ResponseEntity<CustomErrorMessage> handleUserWithEmailAlreadyExists(
            UserWithEmailAlreadyExistsException e, WebRequest request) {

        CustomErrorMessage body = new CustomErrorMessage(HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                e.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    

}
