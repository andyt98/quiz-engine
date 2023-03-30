package com.andy.quizengine.configuration;

import com.andy.quizengine.model.Quiz;
import com.andy.quizengine.model.QuizCompletion;
import com.andy.quizengine.model.QuizUser;
import com.andy.quizengine.repository.QuizCompletionRepository;
import com.andy.quizengine.repository.QuizRepository;
import com.andy.quizengine.repository.QuizUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
public class MockDataLoader implements CommandLineRunner {

    private final QuizUserRepository quizUserRepository;
    private final QuizRepository quizRepository;
    private final QuizCompletionRepository quizCompletionRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MockDataLoader(QuizUserRepository quizUserRepository,
                          QuizRepository quizRepository,
                          QuizCompletionRepository quizCompletionRepository,
                          PasswordEncoder passwordEncoder) {
        this.quizUserRepository = quizUserRepository;
        this.quizRepository = quizRepository;
        this.quizCompletionRepository = quizCompletionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Create some sample users
        List<QuizUser> userList = Arrays.asList(
                new QuizUser("user1@example.com", passwordEncoder.encode("password1")),
                new QuizUser("user2@example.com", passwordEncoder.encode("password2")),
                new QuizUser("user3@example.com", passwordEncoder.encode("password3"))
        );

        quizUserRepository.saveAll(userList);

        List<Quiz> quizList = Arrays.asList(
                new Quiz("Geography Quiz", "What is the capital of France?",
                        List.of("London", "Paris", "Rome", "Berlin"),
                        Set.of(1),
                        userList.get(0)),

                new Quiz("Color Quiz", "Which of the following are primary colors?",
                        List.of("Red", "Green", "Blue", "Yellow", "Cyan", "Magenta"),
                        Set.of(0, 2, 3),
                        userList.get(0)),


                new Quiz("Astronomy Quiz", "What is the smallest planet in our solar system?",
                        List.of("Mercury", "Venus", "Mars", "Jupiter"),
                        Set.of(0),
                        userList.get(0)),

                new Quiz("Literature Quiz", "Who wrote the novel \"To Kill a Mockingbird\"?",
                        List.of("John Steinbeck", "F. Scott Fitzgerald", "Harper Lee", "Ernest Hemingway"),
                        Set.of(2),
                        userList.get(0)),

                new Quiz("Programming Quiz", "Which of the following are common programming languages?",
                        List.of("Java", "Python", "HTML", "CSS", "JavaScript", "C++"),
                        Set.of(0, 1, 4, 5),
                        userList.get(1)),

                new Quiz("Geography Quiz", "What is the currency of Japan?",
                        List.of("Yen", "Dollar", "Euro", "Pound"),
                        Set.of(0),
                        userList.get(1)),

                new Quiz("Weather Quiz", "Which of the following are types of clouds?",
                        List.of("Cirrus", "Stratus", "Cumulus", "Nimbus", "Fog"),
                        Set.of(0, 1, 2, 3),
                        userList.get(1)),

                new Quiz("Art Quiz", "Who painted the famous work \"The Persistence of Memory\"?",
                        List.of("Pablo Picasso", "Salvador Dali", "Vincent van Gogh", "Leonardo da Vinci"),
                        Set.of(1),
                        userList.get(1)),

                new Quiz("Multiple Choice Quiz", "Which of the following are prime numbers?",
                        List.of("2", "4", "5", "6", "11"),
                        Set.of(0, 2, 4),
                        userList.get(2)),

                new Quiz("History Quiz", "Who was the first president of the United States?",
                        List.of("Thomas Jefferson", "Abraham Lincoln", "George Washington", "John Adams"),
                        Set.of(2),
                        userList.get(2)),

                new Quiz("Science Quiz", "Which of the following is a type of electromagnetic radiation?",
                        List.of("Sound waves", "X-rays", "Alpha particles", "Beta particles"),
                        Set.of(1),
                        userList.get(2)),

                new Quiz("Sports Quiz", "Who won the FIFA World Cup in 2018?",
                        List.of("Brazil", "Argentina", "France", "Germany"),
                        Set.of(2),
                        userList.get(2))

        );


        quizRepository.saveAll(quizList);

        List<QuizCompletion> quizCompletionList = Arrays.asList(
                new QuizCompletion(userList.get(0), quizList.get(0), LocalDateTime.now().minusDays(1)),
                new QuizCompletion(userList.get(0), quizList.get(1), LocalDateTime.now().minusDays(1)),
                new QuizCompletion(userList.get(0), quizList.get(2), LocalDateTime.now().minusDays(1)),
                new QuizCompletion(userList.get(0), quizList.get(3), LocalDateTime.now().minusDays(1)),
                new QuizCompletion(userList.get(1), quizList.get(4), LocalDateTime.now().minusDays(2)),
                new QuizCompletion(userList.get(1), quizList.get(5), LocalDateTime.now().minusDays(2)),
                new QuizCompletion(userList.get(1), quizList.get(6), LocalDateTime.now().minusDays(2)),
                new QuizCompletion(userList.get(1), quizList.get(7), LocalDateTime.now().minusDays(2)),
                new QuizCompletion(userList.get(2), quizList.get(8), LocalDateTime.now().minusDays(3)),
                new QuizCompletion(userList.get(2), quizList.get(9), LocalDateTime.now().minusDays(3)),
                new QuizCompletion(userList.get(2), quizList.get(10), LocalDateTime.now().minusDays(3)),
                new QuizCompletion(userList.get(2), quizList.get(11), LocalDateTime.now().minusDays(4))
        );

        quizCompletionRepository.saveAll(quizCompletionList);


    }
}
