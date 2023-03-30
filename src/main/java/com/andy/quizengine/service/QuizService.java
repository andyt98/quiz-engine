package com.andy.quizengine.service;


import com.andy.quizengine.model.*;
import com.andy.quizengine.repository.QuizCompletionRepository;
import com.andy.quizengine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.andy.quizengine.exception.QuizNotFoundException;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizCompletionRepository quizCompletionRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, QuizCompletionRepository quizCompletionRepository) {
        this.quizRepository = quizRepository;
        this.quizCompletionRepository = quizCompletionRepository;
    }

    public Page<Quiz> getQuizzesPaginated(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return quizRepository.findAll(pageable);
    }

    public Quiz getQuizById(long id) {
        return quizRepository
                .findById(id)
                .orElseThrow(QuizNotFoundException::new);
    }

    public void saveQuizCompletion(QuizCompletion quizCompletion) {
        quizCompletionRepository.save(quizCompletion);
    }

    public Page<QuizCompletion> getQuizCompletionsByUser(QuizUser quizUser, int page) {
        Sort sortByCompletedAt = Sort.by("completedAt").descending();
        Pageable pageable = PageRequest.of(page, 10, sortByCompletedAt);
        return quizCompletionRepository.findQuizCompletionByQuizUser(quizUser, pageable);
    }

    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public QuizResponse solveQuiz(long id, UserAnswer userAnswer) {
        return this
                .getQuizById(id)
                .getAnswer()
                .equals(userAnswer.getAnswer())
                ? QuizResponse.CORRECT
                : QuizResponse.WRONG;
    }

    public void deleteQuiz(long id) {
        quizRepository.delete(this.getQuizById(id));
    }
}