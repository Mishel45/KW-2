package ru.skypro.exam_questions_generator.service;

import model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.exam_questions_generator.repository.QuestionRepository;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {
    @Mock
    private QuestionRepository repositoryMock;
    private JavaQuestionService out;

    @BeforeEach
    void setUp() {
        out = new JavaQuestionService(repositoryMock);
    }

    @Test
    void add_WithParameters_ShouldAddAndReturnQuestion() {
        String qText = "Question";
        String aText = "Answer";
        Question expectedQuestion = new Question(qText, aText);
        when(repositoryMock.add(any(Question.class))).thenReturn(expectedQuestion);
        Question actualQuestion = out.add(qText, aText);
        assertEquals(expectedQuestion, actualQuestion);
        verify(repositoryMock, times(1)).add(any(Question.class));
    }

    @Test
    void add_ShouldCallRepository() {
        Question q = new Question("Q1", "A1");
        out.add(q);
        verify(repositoryMock, times(1)).add(q);
    }

    @Test
    void remove_ExistingQuestion_ShouldRemoveAndReturnIt() {
        Question q = out.add("Q1", "A1");
        Question removed = out.remove(new Question("Q1", "A1"));
        assertEquals(q, removed);
        assertFalse(out.getAll().contains(q));
        assertEquals(0, out.getAll().size());
    }

    @Test
    void getRandomQuestion_ShouldReturnQuestionFromList() {
        Question testQuestion = new Question("Q1", "A1");
        when(repositoryMock.getAll()).thenReturn(Set.of(testQuestion));
        Question result = out.getRandomeQuestion();
        assertEquals(testQuestion, result);
    }

    @Test
    void getRandomQuestion_ShouldThrowException_WhenListIsEmpty() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            out.getRandomeQuestion();
        });
        assertEquals("Список вопросов пуст", exception.getMessage());
    }

}