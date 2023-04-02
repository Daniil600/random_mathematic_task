package skypro.course_2.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import skypro.course_2.domain.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ExamServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class ExamServiceImplTest {
    @Autowired
    ExamService examService;

    @MockBean
    QuestionService questionServiceMock;

    @Test
    void getRandom_method_of_ExamService_success() {
        // Подготовка входных данных
        Question question1 = new Question("2148 * 108679", "233442492");
        Question question2 = new Question("1 * 2", "2");
        Question question3 = new Question("1 * 3", "3");
        Question question4 = new Question("1 * 4", "4");
        Question question5 = new Question("1 * 5", "5");
        Set<Question> questionSet = new HashSet<>();

        // Подготовка ожидаемого рез-та
        when(questionServiceMock.getAll()).thenReturn(questionSet);
        // Начало теста

        Set<Question> actualResult = new HashSet<>(questionSet);
        assertEquals(actualResult, examService.getQuestions(5));
    }
}
