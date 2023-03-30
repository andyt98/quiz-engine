package com.andy.quizengine.service;


import com.andy.quizengine.exception.UserWithEmailAlreadyExistsException;
import com.andy.quizengine.model.QuizUser;
import com.andy.quizengine.model.UserDetailsImpl;
import com.andy.quizengine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QuizUser quizUser = getUserByEmail(username);
        return new UserDetailsImpl(quizUser);
    }

    public QuizUser getUserByEmail(String email) {
        return userRepository.getQuizUserByEmail(email).orElseThrow();
    }

    public void registerUser(QuizUser quizUser) {
        String newUserEmail = quizUser.getEmail();
        if (userRepository.existsByEmail(newUserEmail)) {
            throw new UserWithEmailAlreadyExistsException(newUserEmail);
        }
        userRepository.save(new QuizUser(newUserEmail, passwordEncoder.encode(quizUser.getPassword())));
    }
}
