package com.example.coursework2.service;

import com.example.coursework2.model.Question;

import java.util.Collection;
import java.util.List;

public interface QuestionService {

    Question add(String question, String answer);

    Question add(Question question);

    Question remove(String question, String answer);

    Collection<Question> getAll();

    Question getRandomQuestion();

}
