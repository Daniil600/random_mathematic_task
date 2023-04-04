package skypro.course_2.service;

import org.springframework.stereotype.Service;
import skypro.course_2.domain.Question;
import skypro.course_2.exception.ExceptionAlreadyQuestionIsBusy;
import skypro.course_2.exception.ExceptionQuestionIsNotFind;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    public Set<Question> questionSet = new HashSet<>();

    @Override
    public Question questionAdd(String question, String answer){
        Question question1 = new Question(question, answer);
        if(check(question1)){
            throw new ExceptionAlreadyQuestionIsBusy("Этот вопрос уже есть");
        }
        return questionAdd(question1);
    }

    @Override
    public Question questionFind(String question, String answer) {
        Question question1 = new Question(question, answer);
        if(check(question1)){
            return question1;
        }else {
            throw new ExceptionQuestionIsNotFind("Вопрос не найден");
        }
    }

    @Override
    public Question questionAdd(Question question) {
        if(check(question)){
            throw new ExceptionAlreadyQuestionIsBusy("Этот вопрос уже есть");
        }
        questionSet.add(question);
        return question;
    }

    @Override
    public Question questionRemove(String question, String answer) {
        Question question1 = new Question(question, answer);
        if(!check(question1)){
            throw new ExceptionQuestionIsNotFind("Вопрос не найден");
        }
        questionSet.remove(question1);
        return question1;
    }

    @Override
    public Collection<Question> getAll() {
        return questionSet;
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int num1 = random.nextInt(10023);
        int num2 = random.nextInt(214836);
        String question = String.valueOf(num1 + " * " +  num2);
        int num3 = num1 * num2;
        String answer = String.valueOf(num3);
        Question question1 = new Question(question, answer);
        questionSet.add(question1);
        return question1;
    }

    private boolean check(Question question){
        if(questionSet.contains(question)){
            return true;
        }
        return false;
    }
}
