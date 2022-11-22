package br.ufmg.reuso.marcelosg.reprova.model;

import br.ufmg.reuso.marcelosg.reprova.exceptions.StatsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

public class StatsTest {

    @Test
    public void testFromStudentGrades_givenAStatsFail() {
        Collection<StudentGrade> studentGrades =  new ArrayList<>();
        studentGrades.add(new StudentGrade("Foo", 1D ));
        Assertions.assertThrows(NumberFormatException.class, () ->
                Stats.fromStudentGrades(studentGrades));
    }
}
