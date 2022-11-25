package br.ufmg.reuso.marcelosg.reprova.unit.ExamServiceTests;

import br.ufmg.reuso.marcelosg.reprova.model.Exam;
import br.ufmg.reuso.marcelosg.reprova.model.Stats;
import br.ufmg.reuso.marcelosg.reprova.repository.ExamRepository;
import br.ufmg.reuso.marcelosg.reprova.service.ExamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculateExamGradesTest {

    @InjectMocks
    ExamService examService;

    @Mock
    ExamRepository examRepository;

    @Test
    void calculateExamGrades_whenExamIsNull_shouldThrowException() {
        when(examRepository.findById(any())).thenReturn(null);

        assertThrows(Exception.class, () -> examService.calculateExamGrades("1"));
    }

    @Test
    void calculateExamGrades_whenExamHasNoQuestions_shouldThrowParseException() {
        when(examRepository.findById(any())).thenReturn(Optional.of(getSampleExam()));

        assertThrows(Exception.class, () -> examService.calculateExamGrades("1"));
    }

    private Exam getSampleExam() {
        return Exam.builder()
                .id("1234")
                .title("This is a test")
                .semester(1)
                .year(2022)
                .questions(List.of())
                .applicationDate(LocalDateTime.MAX)
                .stats(Stats.builder().max(1D).mean(1D).median(1D).min(1D).standardDeviation(0D).build())
                .discipline("Testing")
                .build();
    }
}
