package br.ufmg.reuso.marcelosg.reprova.strategies;

import br.ufmg.reuso.marcelosg.reprova.model.ExamGeneratorCriteria;
import br.ufmg.reuso.marcelosg.reprova.model.Question;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;
import org.springframework.data.mongodb.core.query.Criteria;
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
    public List<Question> generateExamQuestions(ExamGeneratorCriteria criteria) {

        SampleOperation sampleOperation = Aggregation.sample(criteria.getTotalQuestions());
        MatchOperation filterOperation = Aggregation.match(Criteria.where("tags").in(criteria.getTags()));

        Aggregation aggregation = Aggregation.newAggregation(filterOperation, sampleOperation);

        AggregationResults<Question> results = mongoTemplate.aggregate(aggregation, "question", Question.class);

        return results.getMappedResults();
    }
}
