package br.ufmg.reuso.marcelosg.reprova.controller;

import br.ufmg.reuso.marcelosg.reprova.model.Question;
import br.ufmg.reuso.marcelosg.reprova.model.SemesterGrade;
import br.ufmg.reuso.marcelosg.reprova.service.QuestionService;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@ConditionalOnExpression("${professor_features.active}")
@RequestMapping("/questions")
public class QuestionsController {

    private final QuestionService questionService;

    public QuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/{id}")
    public Question findById(@PathVariable String id) {
        return questionService.findById(id);
    }

    @GetMapping
    public Collection<Question> find() {
        return questionService.find();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Question> deleteById(@PathVariable String id) {
        questionService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Question> create(@RequestBody Question inputQuestion) {
        var question = questionService.createQuestion(inputQuestion);
        return ResponseEntity.status(HttpStatus.CREATED).body(question);
    }

    @PutMapping("/{id}")
    public Question update(@RequestBody Question question, @PathVariable Integer id) {
        return question;
    }

    @PutMapping("/{id}/grades")
    public Question addGrades(@RequestBody SemesterGrade inputGrades, @PathVariable String id) {
        return questionService.addGrades(id, inputGrades);
    }

}
