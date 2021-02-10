package br.ufmg.reuso.marcelosg.reprova.utils;

import br.ufmg.reuso.marcelosg.reprova.model.Question;
import br.ufmg.reuso.marcelosg.reprova.model.Stats;
import br.ufmg.reuso.marcelosg.reprova.model.StudentGrade;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class StatsCalculatorImpl implements StatsCalculator {

    @Override
    public Stats calculateGradesStatistics(List<StudentGrade> studentGrades) {
        return Stats.fromStudentGrades(studentGrades);
    }

    @Override
    public List<StudentGrade> calculateExamStudentGrades(List<Question> examQuestions) {

        Map<String, List<Double>> examGradesByStudent = examQuestions.stream()
                .flatMap(q -> q.getSemesterGrades().get(0).getGrades().stream())
                .collect(Collectors.groupingBy(StudentGrade::getStudent, Collectors.mapping(StudentGrade::getGrade, Collectors.toList())));

        List<StudentGrade> studentGrades = new ArrayList<>();
        examGradesByStudent.forEach((k, v) -> {
            var studentGrade = v.stream().reduce(0.0, Double::sum);
            studentGrades.add(new StudentGrade(k, studentGrade));
        });

        return studentGrades;
    }
}
