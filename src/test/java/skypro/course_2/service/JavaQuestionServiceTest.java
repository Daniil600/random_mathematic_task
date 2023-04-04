package skypro.course_2.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import skypro.course_2.domain.Question;
import skypro.course_2.exception.ExceptionAlreadyQuestionIsBusy;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ContextConfiguration(classes = {JavaQuestionService.class})
@ExtendWith(SpringExtension.class)
public class JavaQuestionServiceTest {

    @Autowired
    QuestionService javaQuestionService;


    public static Stream<Arguments> argument_add_method() {
        return Stream.of(
                Arguments.of("5 + 2", "7", new Question("5 + 2", "7")));
    }

    public static Stream<Arguments> argument_add_method_exception() {
        return Stream.of(
                Arguments.of("2 + 1", "3"));
    }

    public static Stream<Arguments> argument_remove_method_exception() {
        return Stream.of(
                Arguments.of("2 + 2", "4"));
    }

    public static Stream<Arguments> argument_remove_method_success() {
        return Stream.of(
                Arguments.of("2 + 7", "9", new Question("2 + 7", "9")));
    }

    public static Stream<Arguments> argument_getall_method_success() {
        return Stream.of(
                Arguments.of(new Question("2 + 7", "9")),
                Arguments.of(new Question("2 + 1", "3")));
    }


    @ParameterizedTest
    @MethodSource("argument_add_method")
    void add_method_all_param_of_JavaQuestionService_success(String question, String answer, Question expectedResult) {
        Assertions.assertEquals(javaQuestionService.questionAdd(question, answer), expectedResult);
    }


    @ParameterizedTest
    @MethodSource("argument_add_method_exception")
    void add_method_all_param_of_JavaQuestionService_exception(String question, String answer) {
        javaQuestionService.questionAdd(question, answer);
        Exception actualException = assertThrows(
                ExceptionAlreadyQuestionIsBusy.class,
                () -> javaQuestionService.questionAdd(question, answer)
        );
        Assertions.assertEquals("Этот вопрос уже есть", actualException.getMessage());
        javaQuestionService.questionRemove(question, answer);
    }

    @ParameterizedTest
    @MethodSource("argument_remove_method_success")
    void remove_method_all_param_of_JavaQuestionService_success(String question, String answer, Question expectedResult) {
        javaQuestionService.questionAdd(question, answer);
        Assertions.assertEquals(javaQuestionService.questionRemove(question, answer), expectedResult);
    }

    @ParameterizedTest
    @MethodSource("argument_getall_method_success")
    void getAll_method_of_JavaQuestionService_success(Question question) {
        // Подготовка входных данных

        Question question1 = question;


        Set<Question> actualResult = new HashSet<>();

        actualResult.add(question1);

        // Подготовка ожидаемого рез-та

        javaQuestionService.questionAdd(question1);


        // Начало теста

        assertEquals(actualResult, javaQuestionService.getAll());

        javaQuestionService.questionRemove(question.getQuestion(), question.getAnswer());


    }
}
