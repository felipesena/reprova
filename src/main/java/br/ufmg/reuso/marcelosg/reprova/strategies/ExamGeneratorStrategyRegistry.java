package br.ufmg.reuso.marcelosg.reprova.strategies;

import br.ufmg.reuso.marcelosg.reprova.exceptions.UnregisteredStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExamGeneratorStrategyRegistry {

    private List<ExamGeneratorStrategy> strategies;

    public ExamGeneratorStrategyRegistry(List<ExamGeneratorStrategy> strategies) {
        this.strategies = strategies;
    }

    public ExamGeneratorStrategy getStrategy(String strategyType) {
        for (ExamGeneratorStrategy s : strategies) {
            if (s.hasStrategyType(strategyType)) {
                return s;
            }
        }
        throw new UnregisteredStrategy(strategyType);
    }
}
