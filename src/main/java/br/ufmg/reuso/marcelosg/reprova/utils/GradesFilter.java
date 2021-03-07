package br.ufmg.reuso.marcelosg.reprova.utils;

import br.ufmg.reuso.marcelosg.reprova.model.Exam;
import br.ufmg.reuso.marcelosg.reprova.model.Question;

public interface GradesFilter {
    Exam filterExamGradesFromSameYearAndSemester(Exam exam);
}
