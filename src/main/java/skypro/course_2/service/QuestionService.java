package skypro.course_2.service;

import skypro.course_2.domain.Question;
import skypro.course_2.exception.ExceptionAlreadyQuestionIsBusy;

import java.util.Collection;
import java.util.Set;

public interface QuestionService {

    Question questionAdd(String question, String answer);
    Question questionFind(String question, String answer);
    Question questionAdd(Question question);
    Question questionRemove(String question, String answer);
    Collection<Question> getAll();
    Question getRandomQuestion();

}
