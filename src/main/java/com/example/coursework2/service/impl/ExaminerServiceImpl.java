package com.example.coursework2.service.impl;

import com.example.coursework2.model.Question;
import com.example.coursework2.model.exception.IllegalQuestionAmountException;
import com.example.coursework2.service.ExaminerService;
import com.example.coursework2.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private static final Random RANDOM = new Random();

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> allQuestion = questionService.getAll();
        if (allQuestion.size() < amount || amount < 0) {
            throw new IllegalQuestionAmountException(amount);
        } else if (allQuestion.size() == amount) {
            return allQuestion;
        }
        Set<Question> resultSet = new HashSet<>();
        while (resultSet.size() < amount) {
            resultSet.add(questionService.getRandomQuestion());
        }
        return resultSet;
    }
}
