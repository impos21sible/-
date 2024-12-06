package org.main.autoschoolnew.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "schedules", schema = "autoschool")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_schedule")
    private Integer idSchedule;

    @ManyToOne
    @JoinColumn(name = "id_group", nullable = false)
    private Group group;

    @Column(name = "lesson_date", nullable = false)
    private LocalDate lessonDate;

    @Column(name = "lesson_time", nullable = false)
    private LocalTime lessonTime;

    @Column(name = "lesson_duration", nullable = false)
    private String lessonDuration;

    // Для привязки данных в TableView добавим StringProperty
    public StringProperty groupProperty() {
        return new SimpleStringProperty(group != null ? group.getName() : ""); // Название группы
    }

    public StringProperty dateProperty() {
        return new SimpleStringProperty(lessonDate != null ? lessonDate.toString() : "");
    }

    public StringProperty timeProperty() {
        return new SimpleStringProperty(lessonTime != null ? lessonTime.toString() : "");
    }

    public StringProperty instructorProperty() {
        return new SimpleStringProperty(group != null ? group.getInstructorName() : ""); // Предполагается, что есть метод getInstructorName в Group
    }

    // Getters and setters
    public Integer getIdSchedule() { return idSchedule; }
    public void setIdSchedule(Integer idSchedule) { this.idSchedule = idSchedule; }

    public Group getGroup() { return group; }
    public void setGroup(Group group) { this.group = group; }

    public LocalDate getLessonDate() { return lessonDate; }
    public void setLessonDate(LocalDate lessonDate) { this.lessonDate = lessonDate; }

    public LocalTime getLessonTime() { return lessonTime; }
    public void setLessonTime(LocalTime lessonTime) { this.lessonTime = lessonTime; }

    public String getLessonDuration() { return lessonDuration; }
    public void setLessonDuration(String lessonDuration) { this.lessonDuration = lessonDuration; }
}
