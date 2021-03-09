package br.ufmg.reuso.marcelosg.reprova.model;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

@Builder
@Getter
public final class Stats {
    private Double median;
    private Double mean;
    private Double standardDeviation;
    private Double min;
    private Double max;

    public static Stats fromDescriptiveStatistics(DescriptiveStatistics target) {
        return Stats.builder()
                .median(target.getPercentile(50))
                .mean(target.getMean())
                .standardDeviation(new BigDecimal(target.getStandardDeviation()).setScale(2, RoundingMode.HALF_UP).doubleValue())
                .min(target.getMin())
                .max(target.getMax())
                .build();
    }

    public static Stats fromStudentGrades(Collection<StudentGrade> studentGrades) {
        return fromDescriptiveStatistics(new DescriptiveStatistics(studentGrades.stream().mapToDouble(StudentGrade::asDouble).toArray()));
    }
}
