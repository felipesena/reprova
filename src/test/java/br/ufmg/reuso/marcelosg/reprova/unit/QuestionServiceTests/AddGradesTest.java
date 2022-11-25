package br.ufmg.reuso.marcelosg.reprova.unit.QuestionServiceTests;

import br.ufmg.reuso.marcelosg.reprova.exceptions.ItemNotFoundException;
import br.ufmg.reuso.marcelosg.reprova.model.SemesterGrade;
import br.ufmg.reuso.marcelosg.reprova.model.Stats;
import br.ufmg.reuso.marcelosg.reprova.repository.QuestionRepository;
import br.ufmg.reuso.marcelosg.reprova.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddGradesTest {

    @Mock
    QuestionRepository questionRepository;

    @InjectMocks
    QuestionService questionService;

    @Test
    void addGrades_whenQuestionIsNotFound_shouldThrowException() {
        // arrange
        when(questionRepository.findById(anyString())).thenReturn(Optional.empty());

        var sampleGrade = getSampleGrade();
        assertThrows(ItemNotFoundException.class, () -> questionService.addGrades("1", sampleGrade));
    }

    private SemesterGrade getSampleGrade() {
        return SemesterGrade.builder()
                .discipline("Test")
                .year(2022)
                .grades(List.of())
                .semester(1)
                .stats(Stats.builder().max(1D).mean(1D).median(1D).min(1D).standardDeviation(0D).build())
                .build();
    }
}
