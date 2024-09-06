package com.example.coursework2.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionAlreadyAddedException extends RuntimeException {

    public QuestionAlreadyAddedException(String question, String answer) {
        super("Вопрос:[%s] с ответом:[%s] уже добавлен".formatted(question, answer));
    }
}
