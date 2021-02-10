package br.ufmg.reuso.marcelosg.reprova.model;

import lombok.*;
import org.springframework.lang.Nullable;

import java.util.Set;

@Getter
@AllArgsConstructor
public class ExamGeneratorCriteria {
    @NonNull
    private Integer totalQuestions;
    @NonNull
    private String strategyType;
    @NonNull
    private Integer year;
    @NonNull
    private Integer semester;
    @NonNull
    private String title;
    @NonNull
    private boolean saveExam;
    @Nullable
    private Set<String> tags;
}
