package com.example.coursework2.service.impl;

import com.example.coursework2.model.Question;
import com.example.coursework2.model.exception.QuestionAlreadyAddedException;
import com.example.coursework2.model.exception.QuestionsListIsEmptyException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class JavaQuestionServiceTest {

    private final JavaQuestionService javaQuestionService = new JavaQuestionService();

    @Test
    void add_WhenCorrectParams_ThanAddQuestion() {

        String question = "REST?";
        String answer = "NO";

        //test
        Question actual = javaQuestionService.add(question, answer);

        //check
        assertThat(actual).isNotNull();
        assertThat(actual.getQuestion()).isEqualTo(question);
        assertThat(actual.getAnswer()).isEqualTo(answer);
    }

    @Test
    void add_WhenQuestionWithParamsExist_ThanThrowException() {
        String question = "REST?";
        String answer = "NO";
        javaQuestionService.add(question, answer);

        //test && check
        assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(()-> javaQuestionService.add(question, answer));
    }

    @Test
    void add_WhenCorrectQuestion_ThanAddQuestion() {

        String question = "REST?";
        String answer = "NO";

        Question expected = new Question(question, answer);

        //test
        Question actual = javaQuestionService.add(expected);

        //check
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void add_WhenQuestionExist_ThanThrowException() {
        String question = "REST?";
        String answer = "NO";
        javaQuestionService.add(question, answer);

        //test && check
        assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(()-> javaQuestionService.add(question, answer));
    }

    @Test
    void remove_WhenCorrectParams_ThanRemoveQuestion() {
        String question = "REST?";
        String answer = "NO";

        Question expected = javaQuestionService.add(new Question(question, answer));

        //test
        Question actual = javaQuestionService.remove(question, answer);

        //check
        assertThat(actual).isEqualTo(expected);
        Collection<Question> questions = javaQuestionService.getAll();
        assertThat(questions).isEmpty();
    }

    @Test
    void remove_WhenEmptyQuestionList_ThanNotThrowException() {
        //test && check
        assertDoesNotThrow(() -> javaQuestionService.remove
                ("REST?", "NO"));
    }

        @Test
    void getAll_WhenCollectionNotEmpty_ThanReturnAllQuestion() {
        List<Question> expected = Stream.iterate(1, x -> x + 1)
                .limit(nextInt(1, 10))
                .map(x -> new Question("a".repeat(x), "b".repeat(x)))
                .peek(javaQuestionService::add)
                .toList();

        //test
        Collection<Question> actual = javaQuestionService.getAll();

        //check
        assertThat(expected).containsExactlyInAnyOrderElementsOf(actual);
    }

    @Test
    void getAll_WhenCollectionEmpty_ThanReturnEmptyList() {
        //test
        Collection<Question> actual = javaQuestionService.getAll();

        //check
        assertThat(actual).isEmpty();
    }


    @Test
    void getRandomQuestion_WhenCollectionNotEmpty_ThanReturnQuestion() {
        List<Question> expected = new ArrayList<>();
        for (int i = 1; i < nextInt(2, 10); i++) {
            Question question = javaQuestionService.add
                    (new Question("a".repeat(i), "b".repeat(i)));
            expected.add(question);
        }

        //test
        Question actual = javaQuestionService.getRandomQuestion();

        //check
        assertThat(expected).contains(actual);
    }

    @Test
    void getRandomQuestion_WhenEmptyCollection_ThanThrowException() {
        //test && check
        assertThatExceptionOfType(QuestionsListIsEmptyException.class)
                .isThrownBy(javaQuestionService::getRandomQuestion);
    }
}