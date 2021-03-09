package br.ufmg.reuso.marcelosg.reprova.controller;

import br.ufmg.reuso.marcelosg.reprova.aspects.TrackExecutionTime;
import br.ufmg.reuso.marcelosg.reprova.model.Exam;
import br.ufmg.reuso.marcelosg.reprova.model.ExamGeneratorCriteria;
import br.ufmg.reuso.marcelosg.reprova.service.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/exams")
public class ExamsController {

    private ExamService examService;

    public ExamsController(ExamService examService) {
        this.examService = examService;
    }

    @TrackExecutionTime
    @PostMapping("/generator")
    public ResponseEntity<Exam> generateExam(@RequestBody ExamGeneratorCriteria criteria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(examService.generateExam(criteria));
    }

    @TrackExecutionTime
    @PostMapping
    public ResponseEntity<Exam> create(@RequestBody Exam exam) {
        return ResponseEntity.status(HttpStatus.CREATED).body(examService.createExam(exam));
    }

    @TrackExecutionTime
    @GetMapping("/{id}")
    public ResponseEntity<Exam> findById(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(examService.findById(id));
    }

    @TrackExecutionTime
    @GetMapping
    public ResponseEntity<Collection<Exam>> findExams() {
        return ResponseEntity.ok(examService.find());
    }

    @TrackExecutionTime
    @PutMapping("/{id}/grades")
    public ResponseEntity<Exam> calculateExamGrades(@PathVariable("id") String id) {
        return ResponseEntity.ok(examService.calculateExamGrades(id));
    }

    @TrackExecutionTime
    @DeleteMapping("/{id}")
    public ResponseEntity<Exam> deleteById(@PathVariable("id") String id) {
        examService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
