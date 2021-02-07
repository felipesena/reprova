package br.ufmg.reuso.marcelosg.reprova.model;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Builder
@Data
public class Stats {
    private Double average;
    private Double median;
    private Double mean;
    private Double standardDeviation;
    private Double min;
    private Double max;

    static public Stats from_descriptive_statistics(DescriptiveStatistics target) {
        return Stats.builder()
                .median(target.getPercentile(50))
                .mean(target.getMean())
                .standardDeviation(new BigDecimal(target.getStandardDeviation()).setScale(2, RoundingMode.HALF_UP).doubleValue())
                .min(target.getMin())
                .max(target.getMax())
                .build();
    }
}
