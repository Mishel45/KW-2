package ru.skypro.exam_questions_generator.service;

import model.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.skypro.exam_questions_generator.repository.QuestionRepository;

import java.util.*;

@Service
public class MathQuestionService implements QuestionService{
    private final QuestionRepository repository;
    private final Random random = new Random();
    public MathQuestionService(@Qualifier("mathQuestionRepository") QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question add(String question, String answer) {
        return repository.add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        return repository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return repository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return repository.getAll();
    }

    @Override
    public Question getRandomeQuestion() {
        if (repository.getAll().isEmpty()) {
            throw new RuntimeException("Список математических вопросов пуст");
        }

        List<Question> questionList = new ArrayList<>(repository.getAll());
        int index = random.nextInt(questionList.size());
        return questionList.get(index);
    }
}
