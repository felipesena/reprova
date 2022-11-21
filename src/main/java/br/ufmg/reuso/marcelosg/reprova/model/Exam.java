package br.ufmg.reuso.marcelosg.reprova.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@Document("exam")
public class Exam {

    @Id
    private String id;
    @NonNull
    private String title;
    @Nullable
    private String discipline;
    @NonNull
    private Integer year;
    @NonNull
    private Integer semester;

    @Nullable
    private Stats stats;

    @Nullable
    private LocalDateTime applicationDate;

    @NonNull
    @DBRef
    private List<Question> questions;

    // t1  testExtractStudentGrades_whenStudentDoesntHaveGetSemesterGrades
    // t2   testExtractStudentGrades_thenSuccess
    public List<StudentGrade> extractStudentGrades() {

        Map<String, List<Double>> examGradesByStudent = getQuestions().stream().flatMap(q -> {
            assert q.getSemesterGrades() != null;
            if(!q.getSemesterGrades().isEmpty()) {
                return  q.getSemesterGrades().get(0) // Why should I get only the first semester grade?
                        .getGrades().stream();
            }
            return new ArrayList<StudentGrade>().stream();
        }).collect(Collectors.groupingBy(StudentGrade::getStudent, Collectors.mapping(StudentGrade::asDouble, Collectors.toList())));

        List<StudentGrade> studentGradesList = new ArrayList<>();
        examGradesByStudent.forEach((k, v) -> {
            var studentGrade = v.stream().reduce(0.0, Double::sum);
            studentGradesList.add(new StudentGrade(k, studentGrade));
        });
        return studentGradesList;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }
}
