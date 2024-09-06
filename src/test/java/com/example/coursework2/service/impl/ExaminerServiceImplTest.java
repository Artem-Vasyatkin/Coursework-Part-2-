package com.example.coursework2.service.impl;

import com.example.coursework2.model.Question;
import com.example.coursework2.model.exception.IllegalQuestionAmountException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Random;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Mock
    private JavaQuestionService javaQuestionService;

    @Test
    void getQuestions_ReturnsARandomQuestion() {
        List<Question> questions = List.of(
                new Question("ABC", "YES"),
                new Question("BCA", "YES"),
                new Question("CBA", "YES")
        );
        Question expected = questions.get(new Random().nextInt(0, questions.size()));

        when(javaQuestionService.getAll()).thenReturn(questions);
        when(javaQuestionService.getRandomQuestion()).thenReturn(expected);

        //test
        Collection<Question> actual = examinerService.getQuestions(1);

        //check
        assertThat(actual).contains(expected);
        verify(javaQuestionService, only()).getAll();
        verify(javaQuestionService, only()).getRandomQuestion();
    }

    @Test
    void getQuestions_ThrowsAnException_IfANegativeValue_IsPassed() {

        when(javaQuestionService.getAll()).thenThrow(IllegalQuestionAmountException.class);


        //test && check
        assertThatExceptionOfType(IllegalQuestionAmountException.class)
                .isThrownBy(() -> examinerService.getQuestions(-1));
    }

    @Test
    void getQuestions_ThrowsAnException_IfTheNumberOfTransmittedQuestion_ExceedsTheTotalNumber_OfQuestions() {
        List<Question> questions = List.of(
                new Question("ABC", "YES"),
                new Question("BCA", "YES"),
                new Question("CBA", "YES")
        );
        when(javaQuestionService.getAll()).thenReturn(questions);


        //test && check
        assertThatExceptionOfType(IllegalQuestionAmountException.class)
                .isThrownBy(() -> examinerService.getQuestions(questions.size() + 1));

    }
}