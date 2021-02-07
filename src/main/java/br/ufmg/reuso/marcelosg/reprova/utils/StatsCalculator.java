package br.ufmg.reuso.marcelosg.reprova.utils;

import br.ufmg.reuso.marcelosg.reprova.model.Question;
import br.ufmg.reuso.marcelosg.reprova.model.Stats;
import br.ufmg.reuso.marcelosg.reprova.model.StudentGrade;

import java.util.List;

public interface StatsCalculator {
    public Stats calculateGradesStatistics(List<StudentGrade> studentGrades);
    public List<StudentGrade> calculateExamStudentGrades(List<Question> examQuestions);
}
