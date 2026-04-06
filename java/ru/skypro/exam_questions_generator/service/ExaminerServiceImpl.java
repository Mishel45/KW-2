package ru.skypro.exam_questions_generator.service;

import model.Question;
import org.springframework.stereotype.Service;
import ru.skypro.exam_questions_generator.exception.QuestionLimitException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionService.getAll().size() || amount <= 0) {
            throw new QuestionLimitException("Вы запросили некорректное число вопросов (Больше чем есть в списке или меньше одного)");
        }
        Set<Question> examQuestions = new HashSet<>();
        while (examQuestions.size() < amount) {
            examQuestions.add(questionService.getRandomeQuestion());
        }
        return examQuestions;
    }
}
