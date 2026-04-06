package ru.skypro.exam_questions_generator.service;

import model.Question;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class JavaQuestionService implements QuestionService{
    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);
        return newQuestion;
    }
    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }
    @Override
    public Question remove(Question question) {
        questions.remove(question);
        return question;
    }
    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }
    @Override
    public Question getRandomeQuestion() {
        if (questions.isEmpty()) {
            throw new RuntimeException("Список вопросов пуст");
        }
        List<Question> questionList = new ArrayList<>(questions);
        int index = random.nextInt(questionList.size());
        return questionList.get(index);
    }
}
