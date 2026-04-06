package ru.skypro.exam_questions_generator.service;

import model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
