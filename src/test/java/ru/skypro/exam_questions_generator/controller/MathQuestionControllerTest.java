package ru.skypro.exam_questions_generator.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.exam_questions_generator.service.QuestionService;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MathQuestionControllerTest {

    @Mock
    private QuestionService mathService; // Обычный Mock

    private MathQuestionController out;

    @BeforeEach
    void setUp() {
        out = new MathQuestionController(mathService);
    }

    @Test
    void shouldCallServiceWhenAdding() {
        out.addQuestion("2+2", "4");
        verify(mathService).add("2+2", "4");
    }
}