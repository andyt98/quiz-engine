package com.andy.quizengine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "quiz_completion")
public class QuizCompletion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private QuizUser quizUser;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    @JsonIgnore
    private Quiz quiz;

    private LocalDateTime completedAt;

    public QuizCompletion() {
    }

    public QuizCompletion(QuizUser quizUser, Quiz quiz, LocalDateTime completedAt) {
        this.quizUser = quizUser;
        this.quiz = quiz;
        this.completedAt = completedAt;
    }

    @JsonProperty(value = "id")
    public long getQuizId() {
        return this.quiz.getId();
    }


}
