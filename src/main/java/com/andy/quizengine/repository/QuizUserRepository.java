package com.andy.quizengine.repository;


import com.andy.quizengine.model.QuizUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizUserRepository extends JpaRepository<QuizUser, Long> {

    Optional<QuizUser> getQuizUserByEmail(String email);

    boolean existsByEmail(String email);
}
