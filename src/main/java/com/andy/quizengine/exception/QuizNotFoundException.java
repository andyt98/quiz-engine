package com.andy.quizengine.exception;

public class QuizNotFoundException extends RuntimeException {
    public QuizNotFoundException() {
        super("Quiz not found !");
    }
}