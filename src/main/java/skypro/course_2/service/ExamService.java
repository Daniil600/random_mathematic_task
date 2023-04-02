package skypro.course_2.service;

import skypro.course_2.domain.Question;

import java.util.Collection;

public interface ExamService {
    Collection<Question> getQuestions(int amount);

}
