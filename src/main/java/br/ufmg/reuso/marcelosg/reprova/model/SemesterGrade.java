package br.ufmg.reuso.marcelosg.reprova.model;

import lombok.*;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class SemesterGrade {

    @NonNull
    private Integer year;
    @NonNull
    private Integer semester;
    @NonNull
    private String discipline;
    @Nullable
    private Stats stats;
    @NonNull
    private List<StudentGrade> grades;

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SemesterGrade that = (SemesterGrade) o;
        return Objects.equals(year, that.year) &&
                Objects.equals(semester, that.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, semester);
    }
}
