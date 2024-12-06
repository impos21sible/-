package org.main.autoschoolnew.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "experiences", schema = "autoschool")
public class Experience {

    @Id
    @Column(name = "id_experience")
    private Integer idExperience;

    @Column(name = "year")
    private Integer year;

    @Column(name = "date_of_start")
    private LocalDate dateOfStart;

    // Getters and setters
    public Integer getIdExperience() { return idExperience; }
    public void setIdExperience(Integer idExperience) { this.idExperience = idExperience; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public LocalDate getDateOfStart() { return dateOfStart; }
    public void setDateOfStart(LocalDate dateOfStart) { this.dateOfStart = dateOfStart; }

    @Override
    public String toString() {
        return String.valueOf(year); // Возвращает год стажа вместо стандартного хеш-кода
    }
}
