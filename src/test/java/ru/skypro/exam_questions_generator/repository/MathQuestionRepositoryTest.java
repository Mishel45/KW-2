package ru.skypro.exam_questions_generator.repository;

import model.Question;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathQuestionRepositoryTest {
    private final QuestionRepository out = new MathQuestionRepository();
    private final Question q1 = new Question("Q1", "A1");

    @Test
    void shouldAddQuestion() {
        out.add(q1);
        assertTrue(out.getAll().contains(q1));
        assertEquals(1, out.getAll().size());
    }

    @Test
    void shouldRemoveQuestion() {
        out.add(q1);
        out.remove(q1);
        assertFalse(out.getAll().contains(q1));
    }

    @Test
    void shouldReturnAllQuestions() {
        out.add(q1);
        out.add(new Question("Q2", "A2"));
        assertEquals(2, out.getAll().size());
    }
}