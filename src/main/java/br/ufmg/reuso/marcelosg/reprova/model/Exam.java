package br.ufmg.reuso.marcelosg.reprova.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("exam")
public class Exam {

    @Id
    private String id;

    private String title;
    private String discipline;
    private Integer year;
    private Integer semester;

    private Stats stats;
    private List<StudentGrade> studentGrades;

    private LocalDateTime applicationDate;

    @DBRef
    private List<Question> questions;

    public List<StudentGrade> extractStudentGrades() {

        Map<String, List<Double>> examGradesByStudent = getQuestions().stream()
                .flatMap(q -> q.getSemesterGrades().get(0) // Why should I get only the first semester grade?
                        .getGrades().stream())
                .collect(Collectors.groupingBy(StudentGrade::getStudent, Collectors.mapping(StudentGrade::asDouble, Collectors.toList())));

        List<StudentGrade> studentGrades = new ArrayList<>();
        examGradesByStudent.forEach((k, v) -> {
            var studentGrade = v.stream().reduce(0.0, Double::sum);
            studentGrades.add(new StudentGrade(k, studentGrade));
        });

        this.studentGrades = studentGrades;

        return studentGrades;
    }
}
