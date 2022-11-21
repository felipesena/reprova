package br.ufmg.reuso.marcelosg.reprova.unit.ExamServiceTests;

import br.ufmg.reuso.marcelosg.reprova.exceptions.ItemNotFoundException;
import br.ufmg.reuso.marcelosg.reprova.exceptions.StatsException;
import br.ufmg.reuso.marcelosg.reprova.model.*;
import br.ufmg.reuso.marcelosg.reprova.repository.ExamRepository;
import br.ufmg.reuso.marcelosg.reprova.service.ExamService;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExamServiceTest {


    @InjectMocks
    private ExamService examService;

    @Mock
    private  ExamRepository examRepository;


    @Test
    public void testCalculateExamGrades_whenIdIsNull_thenItemNotFoundException() {
        Assertions.assertThrows(ItemNotFoundException.class, () ->
                examService.calculateExamGrades(null));

    }

    @Test
    public void testCalculateExamGrades_thenStatsException() {
        List<SemesterGrade> semesterGrades = new ArrayList<>();
        semesterGrades.add(buildSemesterGrade());
        when(examRepository.findById(anyString())).thenReturn(Optional.of(buildExam("Fooo", semesterGrades)));
        Assertions.assertThrows(StatsException.class, () ->
                examService.calculateExamGrades("BCADFADFERER"));

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
