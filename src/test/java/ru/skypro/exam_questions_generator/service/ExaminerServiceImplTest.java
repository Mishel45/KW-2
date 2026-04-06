package ru.skypro.exam_questions_generator.service;

import model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.exam_questions_generator.exception.QuestionLimitException;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl out;
    private final Question q1 = new Question("Q1", "A1");
    private final Question q2 = new Question("Q2", "A2");
    private final Question q3 = new Question("Q3", "A3");

    @Test
    void getQuestions_ShouldReturnRequestedAmountOfUniqueQuestions() {
        int amount = 2;
        when(questionService.getAll()).thenReturn(List.of(q1, q2, q3));
        when(questionService.getRandomeQuestion()).thenReturn(q1, q2);
        Collection<Question> result = out.getQuestions(amount);
        assertEquals(amount, result.size());
        assertTrue(result.contains(q1));
        assertTrue(result.contains(q2));
        verify(questionService, times(2)).getRandomeQuestion();
    }

    @Test
    void getQuestions_ShouldCallRandomUntilUniqueAmountReached() {
        when(questionService.getAll()).thenReturn(List.of(q1, q2));
        when(questionService.getRandomeQuestion()).thenReturn(q1, q1, q2);
        Collection<Question> result = out.getQuestions(2);
        assertEquals(2, result.size());
        verify(questionService, times(3)).getRandomeQuestion();
    }

    @Test
    void getQuestions_ShouldThrowException_WhenAmountGreaterThanTotal() {
        when(questionService.getAll()).thenReturn(List.of(q1, q2));
        assertThrows(QuestionLimitException.class, () -> out.getQuestions(5));
    }


}