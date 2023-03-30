package com.andy.quizengine.controller;

import com.andy.quizengine.model.QuizUser;
import com.andy.quizengine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/register")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void registerUser(@Valid @RequestBody QuizUser quizUser) {
        userService.registerUser(quizUser);
    }
    
}
