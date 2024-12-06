package org.main.autoschoolnew.models;

import jakarta.persistence.*;

@Entity
@Table(name = "student_progress", schema = "autoschool")
public class StudentProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_progress")
    private Integer idProgress;

    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_schedule", nullable = false)
    private Schedule schedule; // Используется правильный путь

    @Column(name = "grade", length = 10)
    private String grade;

    @Column(name = "feedback", columnDefinition = "TEXT")
    private String feedback;

    // Getters and setters
    public Integer getIdProgress() { return idProgress; }
    public void setIdProgress(Integer idProgress) { this.idProgress = idProgress; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Schedule getSchedule() { return schedule; }
    public void setSchedule(Schedule schedule) { this.schedule = schedule; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
}
