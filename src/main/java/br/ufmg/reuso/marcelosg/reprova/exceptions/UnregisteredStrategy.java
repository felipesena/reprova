package br.ufmg.reuso.marcelosg.reprova.exceptions;

public class UnregisteredStrategy extends RuntimeException {
    public UnregisteredStrategy(String strategy) {
        super(strategy + " could not be found at ExamGeneratorStrategyRegistry");
    }
}
