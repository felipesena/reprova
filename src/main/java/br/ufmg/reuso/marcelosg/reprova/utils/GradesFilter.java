package br.ufmg.reuso.marcelosg.reprova.utils;

import br.ufmg.reuso.marcelosg.reprova.model.Exam;

public interface GradesFilter {
    Exam filterExamGradesFromSameYearAndSemester(Exam exam);
}
