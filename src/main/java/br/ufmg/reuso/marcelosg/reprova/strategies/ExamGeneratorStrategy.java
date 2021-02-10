package br.ufmg.reuso.marcelosg.reprova.strategies;

import br.ufmg.reuso.marcelosg.reprova.model.ExamGeneratorCriteria;
import br.ufmg.reuso.marcelosg.reprova.model.Question;

import java.util.List;

public interface ExamGeneratorStrategy {
    boolean hasStrategyType(String strategyType);

    List<Question> generateExamQuestions(ExamGeneratorCriteria criteria);
}
