package br.ufmg.reuso.marcelosg.reprova.model;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExamTest {


    @Test
    public void testExtractStudentGrades_whenStudentDoesntHaveGetSemesterGrades() {
        Exam exam =  buildExam("Foo ", null);
        Assertions.assertTrue( exam.extractStudentGrades().isEmpty());

    }
    @Test
    public void testExtractStudentGrades_withThenSucces() {
        List<SemesterGrade> semesterGrades = new ArrayList<>();
        semesterGrades.add(buildSemesterGrade());
        Exam exam =  buildExam("Foo ", semesterGrades);
        Assertions.assertEquals( 1 , exam.extractStudentGrades().size());
    }



    private Exam buildExam(String title,  List<SemesterGrade> semesterGrades) {
        List<Question> questionList = new ArrayList<>();
        questionList.add(new Question( "aL", "adfadf", "adfadsf", "sdfadf",
                null, Collections.emptyList(), Collections.emptySet(), Boolean.TRUE, null,
                QuestionDifficulty.EASY, null, semesterGrades  ));
        return new Exam( null, title, null, 2022, 1, null, null , questionList);
    }

    private SemesterGrade buildSemesterGrade() {

        double[] array =  { 1,2,3,5,6,7};
        DescriptiveStatistics descriptive =  new DescriptiveStatistics(array);

        Stats stats = Stats.fromDescriptiveStatistics(descriptive);
        List<StudentGrade> grades = new ArrayList<>();
        grades.add(new StudentGrade("Fulano", 5D));

        return  new SemesterGrade(2022 , 1, "Matematica", stats, grades);
    }

}
