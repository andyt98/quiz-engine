package com.andy.quizengine.controller;

import com.andy.quizengine.model.*;
import com.andy.quizengine.service.QuizService;
import com.andy.quizengine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;
    private final UserService userService;


    @Autowired
    public QuizController(QuizService quizService, UserService userService) {
        this.quizService = quizService;
        this.userService = userService;
    }

    @GetMapping
    public Page<Quiz> getQuizzes(@RequestParam(required = false) Integer page) {
        if (page == null) {
            page = 0;
        }
        return quizService.getQuizzesPaginated(page);
    }

    @GetMapping(value = "/{id}")
    public Quiz getQuiz(@PathVariable long id) {
        return quizService.getQuizById(id);
    }

    @PostMapping(value = "/{id}/solve")
    public QuizResponse solveQuiz(@PathVariable long id, @RequestBody UserAnswer userAnswer,
                                  @AuthenticationPrincipal UserDetails userDetails) {
        QuizResponse quizResponse = quizService.solveQuiz(id, userAnswer);
        if (quizResponse.isSuccess()) {
            String userEmail = userDetails.getUsername();
            QuizUser quizUser = userService.getUserByEmail(userEmail);
            Quiz quiz = quizService.getQuizById(id);
            QuizCompletion quizCompletion = new QuizCompletion(quizUser, quiz, LocalDateTime.now());
            quizService.saveQuizCompletion(quizCompletion);
        }
        return quizResponse;
    }

    @PostMapping
    public Quiz createQuiz(@Valid @RequestBody Quiz quiz, @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        QuizUser quizUser = userService.getUserByEmail(email);
        Quiz newQuiz = new Quiz(quiz.getTitle(), quiz.getText(), quiz.getOptions(), quiz.getAnswer(), quizUser);
        return quizService.addQuiz(newQuiz);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteQuiz(@PathVariable long id, @AuthenticationPrincipal UserDetails userDetails) {
        Quiz quizToBeDeleted = quizService.getQuizById(id);
        String userEmail = userDetails.getUsername();
        QuizUser quizUser = userService.getUserByEmail(userEmail);
        if (quizToBeDeleted == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (quizToBeDeleted.getAuthor().getId() != quizUser.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        quizService.deleteQuiz(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/completed")
    public Page<QuizCompletion> getCompletedQuizzes(@AuthenticationPrincipal UserDetails userDetails,
                                                    @RequestParam(required = false) Integer page) {
        if (page == null) {
            page = 0;
        }
        String userEmail = userDetails.getUsername();
        QuizUser quizUser = userService.getUserByEmail(userEmail);
        return quizService.getQuizCompletionsByUser(quizUser, page);
    }

}

