package ru.skypro.exam_questions_generator.service;

import model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.exam_questions_generator.repository.QuestionRepository;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceTest {

    @Mock
    private QuestionRepository repositoryMock;

    @InjectMocks
    private MathQuestionService out;

    private final Question q1 = new Question("M1", "A1");
    private final Question q2 = new Question("M2", "A2");

    @Test
    void shouldAddQuestion() {
        when(repositoryMock.add(q1)).thenReturn(q1);
        Question result = out.add(q1);
        assertEquals(q1, result);
        verify(repositoryMock, times(1)).add(q1);
    }

    @Test
    void getRandomQuestion_ShouldReturnQuestion() {
        when(repositoryMock.getAll()).thenReturn(Set.of(q1, q2));
        Question result = out.getRandomeQuestion();
        assertTrue(Set.of(q1, q2).contains(result));
    }

    @Test
    void getRandomQuestion_ShouldThrowExceptionWhenEmpty() {
        when(repositoryMock.getAll()).thenReturn(Collections.emptySet());
        assertThrows(RuntimeException.class, () -> out.getRandomeQuestion());
    }

    @Test
    void shouldReturnAllQuestions() {
        when(repositoryMock.getAll()).thenReturn(Set.of(q1, q2));
        assertEquals(2, out.getAll().size());
    }
}