package br.ufmg.reuso.marcelosg.reprova.service;

import br.ufmg.reuso.marcelosg.reprova.exceptions.ItemNotFoundException;
import br.ufmg.reuso.marcelosg.reprova.model.Exam;
import br.ufmg.reuso.marcelosg.reprova.model.ExamGeneratorCriteria;
import br.ufmg.reuso.marcelosg.reprova.model.Stats;
import br.ufmg.reuso.marcelosg.reprova.repository.ExamRepository;
import br.ufmg.reuso.marcelosg.reprova.strategies.ExamGeneratorStrategyRegistry;
import lombok.var;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class ExamService {

    private final ExamRepository examRepository;

    private final ExamGeneratorStrategyRegistry strategyRegistry;

    public ExamService(ExamRepository examRepository, ExamGeneratorStrategyRegistry strategyRegistry) {
        this.examRepository = examRepository;
        this.strategyRegistry = strategyRegistry;
    }

    public Exam generateExam(ExamGeneratorCriteria criteria) {
        var strategy = strategyRegistry.getStrategy(criteria.getStrategyType());
        var questions = strategy.generateExamQuestions(criteria);

        var generatedExam = Exam.builder()
                .questions(questions)
                .title(criteria.getTitle())
                .year(criteria.getYear())
                .semester(criteria.getSemester())
                .build();

        if (criteria.isSaveExam()) {
            this.createExam(generatedExam);
        }
        return generatedExam;
    }

    public Exam calculateExamGrades(String id) {
        var exam = this.findById(id);
        var examStats = Stats.fromStudentGrades(exam.extractStudentGrades());
        exam.setStats(examStats);

        return examRepository.save(exam);
    }

    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    public Exam findById(String id) {
        return examRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Collection<Exam> find() {
        return examRepository.findAll(Sort.by("title").ascending());
    }

    public Boolean deleteById(String id) {
        examRepository.deleteById(id);
        return Boolean.TRUE;
    }
}
