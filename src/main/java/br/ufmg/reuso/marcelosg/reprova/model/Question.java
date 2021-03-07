package br.ufmg.reuso.marcelosg.reprova.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor
@Document("question")
public class Question {

    @Id
    private String id;

    /**
     * The description of the question. Mustn't be null nor empty.
     */
    @NonNull
    private String description;
    /**
     * The statement of the question. May be null or empty.
     */
    @NonNull
    private String statement;

    @Nullable
    private String discipline;
    @Nullable
    private Stats stats;

    @NonNull
    private List<String> themes;

    @NonNull
    private Set<String> tags;

    /**
     * Whether the question is private.
     */
    @NonNull
    private Boolean pvt;

    @Nullable
    private Integer estimateTimeInMinutes;

    @NonNull
    private QuestionDifficulty difficulty;

    @Nullable
    private String filePath;

    @Nullable
    List<SemesterGrade> semesterGrades;
}
