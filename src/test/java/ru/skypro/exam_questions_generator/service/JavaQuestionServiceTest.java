package ru.skypro.exam_questions_generator.service;

import model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    private JavaQuestionService out;

    @BeforeEach
    void setUp() {
        out = new JavaQuestionService();
    }

    @Test
    void add_WithParameters_ShouldAddAndReturnQuestion() {
        Question result = out.add("Java", "Language");
        assertEquals(new Question("Java", "Language"), result);
        assertEquals(1, out.getAll().size());
        assertTrue(out.getAll().contains(result));
    }

    @Test
    void add_Duplicate_ShouldNotAddSameQuestionTwice() {
        out.add("Java", "Language");
        out.add("Java", "Language");
        assertEquals(1, out.getAll().size()); // Set не даст добавить дубликат
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
        out.add("Q1", "A1");
        out.add("Q2", "A2");
        Question result = out.getRandomeQuestion();
        assertNotNull(result);
        assertTrue(out.getAll().contains(result));
    }

    @Test
    void getRandomQuestion_ShouldThrowException_WhenListIsEmpty() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            out.getRandomeQuestion();
        });
        assertEquals("Список вопросов пуст", exception.getMessage());
    }

}