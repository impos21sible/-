package org.main.autoschoolnew.models;

import java.io.Serializable;
import java.util.Objects;

public class StudentAttendancePK implements Serializable {

    private Integer student;
    private Integer schedule;

    // Getters and setters
    public Integer getStudent() { return student; }
    public void setStudent(Integer student) { this.student = student; }

    public Integer getSchedule() { return schedule; }
    public void setSchedule(Integer schedule) { this.schedule = schedule; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentAttendancePK that = (StudentAttendancePK) o;
        return Objects.equals(student, that.student) && Objects.equals(schedule, that.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, schedule);
    }
}
