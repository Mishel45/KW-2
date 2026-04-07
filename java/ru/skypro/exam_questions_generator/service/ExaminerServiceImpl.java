package ru.skypro.exam_questions_generator.service;

import model.Question;
import org.springframework.stereotype.Service;
import ru.skypro.exam_questions_generator.exception.QuestionLimitException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final List<QuestionService> questionService;
    private final Random random = new Random();

    public ExaminerServiceImpl(List<QuestionService> questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        List<Question> allQuestions = questionService.stream().flatMap(service -> service.getAll().stream()).collect(Collectors.toList());
        if (amount > allQuestions.size() || amount <= 0) {
            throw new QuestionLimitException("Вы запросили некорректное число вопросов (Больше чем есть в списке или меньше одного)");
        }
        Set<Question> examQuestions = new HashSet<>();
        while (examQuestions.size() < amount) {
            int index = random.nextInt(allQuestions.size());
            examQuestions.add(allQuestions.get(index));
        }
        return examQuestions;
    }
}
