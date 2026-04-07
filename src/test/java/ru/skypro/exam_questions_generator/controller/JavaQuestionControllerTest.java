package ru.skypro.exam_questions_generator.controller;

import model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.exam_questions_generator.service.QuestionService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JavaQuestionControllerTest {
    @Mock
    private QuestionService questionService;

    @InjectMocks
    private JavaQuestionController out;

    @Test
    void add_ShouldCallServiceAndReturnQuestion() {
        Question q = new Question("Q", "A");
        when(questionService.add("Q", "A")).thenReturn(q);
        assertEquals(q, out.addQuestion("Q", "A"));
        verify(questionService).add("Q", "A");
    }

    @Test
    void remove_ShouldCallServiceAndReturnQuestion() {
        Question q = new Question("Q", "A");
        when(questionService.remove(q)).thenReturn(q);
        assertEquals(q, out.removeQuestion("Q", "A"));
        verify(questionService).remove(q);
    }

    @Test
    void getAll_ShouldReturnCollection() {
        List<Question> questions = List.of(new Question("Q", "A"));
        when(questionService.getAll()).thenReturn(questions);
        assertEquals(questions, out.qetQuestions());
    }
}