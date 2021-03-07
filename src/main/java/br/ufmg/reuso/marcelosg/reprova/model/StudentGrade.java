package br.ufmg.reuso.marcelosg.reprova.model;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public final class StudentGrade {
    @NonNull
    private String student;
    @NonNull
    private Double grade;
    public String getStudent() {
        return student;
    }
    public Double asDouble() {
        return grade;
    }
}
