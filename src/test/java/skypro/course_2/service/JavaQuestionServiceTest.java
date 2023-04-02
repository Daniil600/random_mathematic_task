package skypro.course_2.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import skypro.course_2.domain.Question;
import skypro.course_2.exception.ExceptionAlreadyQuestionIsBusy;
import skypro.course_2.exception.ExceptionQuestionIsNotFind;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {JavaQuestionService.class})
@ExtendWith(SpringExtension.class)
public class JavaQuestionServiceTest {

    @Autowired
    QuestionService javaQuestionService;
    @MockBean
    private QuestionService javaQuestionServiceMock;

    public static Stream<Arguments> argument_add_method() {
        return Stream.of(
                Arguments.of("2 + 2", "4", new Question("2 + 2", "4")));
    }
    public static Stream<Arguments> argument_add_method_exception() {
        return Stream.of(
                Arguments.of("2 + 2", "4"));
    }

    public static Stream<Arguments> argument_remove_method_exception() {
        return Stream.of(
                Arguments.of("2 + 2", "4"));
    }
    public static Stream<Arguments> argument_remove_method_success() {
        return Stream.of(
                Arguments.of("2 + 2", "4", new Question("2 + 2", "4")));
    }


    @ParameterizedTest
    @MethodSource("argument_add_method")
    void add_method_all_param_of_JavaQuestionService_success(String question, String answer, Question expectedResult) {
        Assertions.assertEquals(javaQuestionService.questionAdd(question, answer), expectedResult);
    }


    @Test
    void getRandom_method_of_JavaQuestionService_success() {
        // Подготовка входных данных
        Question question = new Question("2148 * 108679", "233442492");

        // Подготовка ожидаемого рез-та
        when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(question);
        // Начало теста

        Question actualResult = new Question("2148 * 108679", "233442492");
        assertEquals(actualResult, javaQuestionService.getRandomQuestion());
    }
    @ParameterizedTest
    @MethodSource("argument_add_method_exception")
    void add_method_all_param_of_JavaQuestionService_exception(String question, String answer) {
        Exception exception = new ExceptionAlreadyQuestionIsBusy("Этот вопрос уже есть");
        javaQuestionService.questionAdd(question, answer);
        Assertions.assertEquals(javaQuestionService.questionAdd(question, answer), exception.getMessage());
    }


    @ParameterizedTest
    @MethodSource("argument_remove_method_exception")
    void remove_method_all_param_of_JavaQuestionService_exception(String question, String answer) {
        Exception exception = new ExceptionQuestionIsNotFind("Вопрос не найден");
        javaQuestionService.questionAdd("3 + 2", "5");
        Assertions.assertEquals(javaQuestionService.questionRemove(question, answer), exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("argument_remove_method_success")
    void remove_method_all_param_of_JavaQuestionService_success(String question, String answer, Question expectedResult) {
        javaQuestionService.questionAdd(question, answer);
        Assertions.assertEquals(javaQuestionService.questionRemove(question, answer), expectedResult);
    }

    @Test
    void getAll_method_of_JavaQuestionService_success() {
        // Подготовка входных данных
        Question question1 = new Question("2148 * 108679", "233442492");
        Question question2 = new Question("1 * 2", "2");
        Question question3 = new Question("1 * 3", "3");
        Question question4 = new Question("1 * 4", "4");
        Question question5 = new Question("1 * 5", "5");
        Set<Question> questionSet = new HashSet<>();

        // Подготовка ожидаемого рез-та
        when(javaQuestionServiceMock.getAll()).thenReturn(questionSet);
        // Начало теста

        Set<Question> actualResult = new HashSet<>(questionSet);
        assertEquals(actualResult, javaQuestionServiceMock.getAll());
    }
}
