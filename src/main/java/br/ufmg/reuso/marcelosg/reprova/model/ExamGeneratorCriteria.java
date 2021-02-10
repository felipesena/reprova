package br.ufmg.reuso.marcelosg.reprova.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamGeneratorCriteria {
    private Integer totalQuestions;
    private String strategyType;
    private Integer year;
    private Integer semester;
    private String title;
    private boolean saveExam;
}
