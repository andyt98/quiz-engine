package com.andy.quizengine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "quiz_user")
public class QuizUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email address format")
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 5, message = "At least 5 characters are required")
    private String password;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Quiz> createdQuizzes;

    @OneToMany(
            mappedBy = "quizUser",
            cascade = CascadeType.REMOVE
    )
    @JsonIgnore
    private List<QuizCompletion> quizCompletions;

    public QuizUser() {
    }

    public QuizUser(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
