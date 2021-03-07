package br.ufmg.reuso.marcelosg.reprova.utils;

import br.ufmg.reuso.marcelosg.reprova.model.Exam;
import br.ufmg.reuso.marcelosg.reprova.model.Question;
import org.springframework.stereotype.Component;

@Component
public class GradesFilterImpl implements GradesFilter {

    @Override
    public Exam filterExamGradesFromSameYearAndSemester(Exam exam) {
        exam.getQuestions().forEach(q -> filterQuestionGrades(exam.getYear(), exam.getSemester(), q));
        return exam;
    }

    private void filterQuestionGrades(Integer year, Integer semester, Question question) {
        question.getSemesterGrades().removeIf(grade -> !grade.getYear().equals(year) || !grade.getSemester().equals(semester));
    }
}
