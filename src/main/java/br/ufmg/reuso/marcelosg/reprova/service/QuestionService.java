package br.ufmg.reuso.marcelosg.reprova.service;

import br.ufmg.reuso.marcelosg.reprova.exceptions.ItemNotFoundException;
import br.ufmg.reuso.marcelosg.reprova.exceptions.ValidationException;
import br.ufmg.reuso.marcelosg.reprova.model.Question;
import br.ufmg.reuso.marcelosg.reprova.model.SemesterGrade;
import br.ufmg.reuso.marcelosg.reprova.model.Stats;
import br.ufmg.reuso.marcelosg.reprova.repository.QuestionRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question findById(String id) {
        return questionRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Collection<Question> find() {
        return questionRepository.findAll(Sort.by("statement").ascending());
    }

    public Boolean delete(String id) {
        questionRepository.deleteById(id);
        return true;
    }

    public Question createQuestion(Question question) {
        questionRepository.save(question);

        log.info("Question saved. statement=\"{}\" id={}", question.getStatement(), question.getId());
        return question;
    }

    public Question addGrades(@NonNull String questionId, @NonNull SemesterGrade inputGrades) {
        var question = questionRepository.findById(questionId).orElseThrow(() -> new ItemNotFoundException(questionId));

        var stats = Stats.fromStudentGrades(inputGrades.getGrades());
        inputGrades.setStats(stats);

        var existingGradeIndex = question.getSemesterGrades().indexOf(inputGrades);
        if (existingGradeIndex >= 0) {
            question.getSemesterGrades().set(existingGradeIndex, inputGrades);
            log.info("Updated existing semester grade year={} semester={} on question={}", inputGrades.getYear(), inputGrades.getSemester(), questionId);

        } else {
            question.getSemesterGrades().add(inputGrades);
            log.info("Semester Grade for year={} semester={} added to question={}", inputGrades.getYear(), inputGrades.getSemester(), questionId);
        }

        questionRepository.save(question);
        return question;
    }
}
