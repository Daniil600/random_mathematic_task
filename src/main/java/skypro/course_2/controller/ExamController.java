package skypro.course_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import skypro.course_2.domain.Question;
import skypro.course_2.service.ExamService;
import skypro.course_2.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class ExamController {
    @Autowired
    ExamService examService;

    public ExamController(ExamService examService, QuestionService questionService) {
        this.examService = examService;
    }

    @GetMapping(path = "/questions")
    public Collection<Question> questions (@RequestParam int amount){
        return examService.getQuestions(amount);
    }
}
