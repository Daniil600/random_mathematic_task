package skypro.course_2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skypro.course_2.domain.Question;

import java.util.Collection;
import java.util.HashSet;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    QuestionService questionService;

    public ExamServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        for (int i = 0; i <= amount; i++) {
            questionService.getRandomQuestion();
        }
        return questionService.getAll();
    }
}
