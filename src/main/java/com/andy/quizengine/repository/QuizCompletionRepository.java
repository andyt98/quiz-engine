package com.andy.quizengine.repository;


import com.andy.quizengine.model.QuizCompletion;
import com.andy.quizengine.model.QuizUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCompletionRepository extends JpaRepository<QuizCompletion, Long> {
    Page<QuizCompletion> findQuizCompletionByQuizUser(QuizUser quizUser, Pageable pageable);
}
