package com.example.coursework2.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalQuestionAmountException extends RuntimeException {

    public IllegalQuestionAmountException(int amount) {
        super("[%s] сумма недействительна для суммы отделов".formatted(amount));
    }
}
