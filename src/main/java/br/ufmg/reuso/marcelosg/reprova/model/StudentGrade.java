package br.ufmg.reuso.marcelosg.reprova.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public final class StudentGrade {
    private String student;
    private Double grade;
    public String getStudent() {
        return student;
    }
    public Double asDouble() {
        return grade;
    }
}
