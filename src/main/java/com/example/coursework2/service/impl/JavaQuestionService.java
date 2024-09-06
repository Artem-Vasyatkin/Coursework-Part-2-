package com.example.coursework2.service.impl;

import com.example.coursework2.model.Question;
import com.example.coursework2.model.exception.QuestionAlreadyAddedException;
import com.example.coursework2.model.exception.QuestionsListIsEmptyException;
import com.example.coursework2.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Collections.emptyListIterator;
import static java.util.Collections.unmodifiableCollection;
import static org.apache.commons.lang3.RandomUtils.nextInt;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> javaQuestion = new HashSet<>();

    @Override
    public Question add(String question, String answer) {
        Question result = new Question(question, answer);

        if (javaQuestion.contains(result)) {
            throw new QuestionAlreadyAddedException(question, answer);
        }
        javaQuestion.add(result);

        return result;
    }

    @Override
    public Question add(Question question) {

        if (javaQuestion.contains(question)) {
            throw new QuestionAlreadyAddedException(question.getQuestion(), question.getAnswer());
        }
        javaQuestion.add(question);

        return question;
    }

    @Override
    public Question remove(String question, String answer) {
        Question result = new Question(question, answer);
        javaQuestion.remove(result);

        return result;
    }

    @Override
    public Collection<Question> getAll() {
        return unmodifiableCollection(javaQuestion);
    }

    @Override
    public Question getRandomQuestion() {
        if (javaQuestion.isEmpty()) {
            throw new QuestionsListIsEmptyException();
        }
        return new ArrayList<>(javaQuestion).get(nextInt(0, javaQuestion.size() ));
    }
}
