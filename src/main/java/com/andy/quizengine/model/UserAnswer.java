package com.andy.quizengine.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class UserAnswer {

    private Set<Integer> answer;
}