package com.andy.quizengine.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum QuizResponse {

    WRONG(false, "Wrong answer! Please, try again."),
    CORRECT(true, "Congratulations, you're right!");

    private final boolean success;
    private final String feedback;

    QuizResponse(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }
}
