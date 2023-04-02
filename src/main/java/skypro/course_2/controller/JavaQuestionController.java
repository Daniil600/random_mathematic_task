package skypro.course_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import skypro.course_2.domain.Question;
import skypro.course_2.exception.ExceptionAlreadyQuestionIsBusy;
import skypro.course_2.exception.ExceptionQuestionIsNotFind;
import skypro.course_2.service.QuestionService;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("exam/java")
public class JavaQuestionController {
    @Autowired
    QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path = "add")
    public Question addQuestion(@RequestParam(required = false) String question, @RequestParam(required = false) String answer){
        return questionService.questionAdd(question, answer);
    }
    @GetMapping()
    public Collection<Question> getall(){
        return questionService.getAll();
    }
    @GetMapping(path = "get_random")
    public Question getRandom(){
        return questionService.getRandomQuestion();
    }
    @GetMapping(path = "remove")
    public Question remove(@RequestParam(required = false) String question, @RequestParam(required = false) String answer){
        return questionService.questionRemove(question, answer);
    }

    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    @ExceptionHandler({ExceptionAlreadyQuestionIsBusy.class, ExceptionQuestionIsNotFind.class})
    public String handleException(RuntimeException e){
        return String.format("%s %s", HttpStatus.PAYLOAD_TOO_LARGE.value(), e.getMessage());
    }
}
