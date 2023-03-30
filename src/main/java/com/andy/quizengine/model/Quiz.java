package com.andy.quizengine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    @NotNull
    @Size(min = 2)
    @ElementCollection
    @CollectionTable(
            name = "quiz_options",
            joinColumns = @JoinColumn(name = "quiz_id"))
    @Column(name = "quiz_option")
    private List<String> options;

    @ElementCollection
    @CollectionTable(
            name = "quiz_answers",
            joinColumns = @JoinColumn(name = "quiz_id"))
    @Column(name = "quiz_answer")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Integer> answer;

    @ManyToOne
    @JoinColumn(name = "authorId")
    @JsonIgnore
    private QuizUser author;

    @OneToMany(
            mappedBy = "quiz",
            cascade = CascadeType.REMOVE
    )
    @JsonIgnore
    private List<QuizCompletion> quizCompletions;

    public Quiz() {
    }

    public Quiz(String title, String text, List<String> options, Set<Integer> answer, QuizUser quizUser) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
        this.author = quizUser;
    }

}