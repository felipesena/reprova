package br.ufmg.reuso.marcelosg.reprova.strategies;

import br.ufmg.reuso.marcelosg.reprova.model.Question;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagsExamGeneratorStrategy implements ExamGeneratorStrategy {

    private MongoTemplate mongoTemplate;

    public TagsExamGeneratorStrategy(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public boolean hasStrategyType(String strategyType) {
        return strategyType.equalsIgnoreCase("tags");
    }

    @Override
    public List<Question> generateExamQuestions(int totalQuestions) {
        return null;
    }
}
