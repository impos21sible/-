package org.main.autoschoolnew.models;

import jakarta.persistence.*;
import org.main.autoschoolnew.models.Schedule;

@Entity
@Table(name = "student_attendance", schema = "autoschool")
@IdClass(StudentAttendancePK.class)
public class StudentAttendance {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false)
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_schedule", nullable = false)
    private Schedule schedule; // Используется правильный путь

    @Column(name = "attendance_status", length = 20, nullable = false)
    private String attendanceStatus;

    // Getters and setters
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Schedule getSchedule() { return schedule; }
    public void setSchedule(Schedule schedule) { this.schedule = schedule; }

    public String getAttendanceStatus() { return attendanceStatus; }
    public void setAttendanceStatus(String attendanceStatus) { this.attendanceStatus = attendanceStatus; }
}
