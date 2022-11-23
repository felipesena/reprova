package br.ufmg.reuso.marcelosg.reprova.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

class StatsTest {

    @Test
    void testFromStudentGrades_givenAStatsFail() {
        Collection<StudentGrade> studentGrades =  new ArrayList<>();
        studentGrades.add(new StudentGrade("Foo", 1D ));
        Assertions.assertThrows(NumberFormatException.class, () ->
                Stats.fromStudentGrades(studentGrades));
    }
}
