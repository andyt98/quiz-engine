package com.andy.quizengine.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
class CustomErrorMessage {
    private int statusCode;
    private LocalDateTime timestamp;
    private String message;
    private String description;

}
