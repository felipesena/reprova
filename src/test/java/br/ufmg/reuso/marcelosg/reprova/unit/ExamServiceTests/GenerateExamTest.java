package br.ufmg.reuso.marcelosg.reprova.unit.ExamServiceTests;

import br.ufmg.reuso.marcelosg.reprova.model.ExamGeneratorCriteria;
import br.ufmg.reuso.marcelosg.reprova.service.ExamService;
import br.ufmg.reuso.marcelosg.reprova.strategies.ExamGeneratorStrategyRegistry;
import br.ufmg.reuso.marcelosg.reprova.strategies.RandomExamGeneratorStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenerateExamTest {

    @InjectMocks
    ExamService examService;

    @Mock
    ExamGeneratorStrategyRegistry strategyRegistry;

    @Mock
    RandomExamGeneratorStrategy randomExamGeneratorStrategy;

    @Test
    void generateExam_whenCriteriaIsValid_shouldBeSuccessful() {
        // arrange
        var criteria = new ExamGeneratorCriteria(10, "Test",
                2022, 2, "This is a test", false, null);

        when(randomExamGeneratorStrategy.generateExamQuestions(any())).thenReturn(List.of());
        when(strategyRegistry.getStrategy(anyString())).thenReturn(randomExamGeneratorStrategy);

        // act
        var exam = examService.generateExam(criteria);

        // assert
        assertEquals("This is a test", exam.getTitle());
        assertTrue(exam.getQuestions().isEmpty());
    }
}
