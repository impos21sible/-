package org.main.autoschoolnew.models;

import jakarta.persistence.*;

@Entity
@Table(name = "student_statuses", schema = "autoschool")
public class StudentStatus {

    @Id
    @Column(name = "id_status")
    private Integer idStatus;

    @Column(name = "name", nullable = false)
    private String name;

    // Getters and setters
    public Integer getIdStatus() { return idStatus; }
    public void setIdStatus(Integer idStatus) { this.idStatus = idStatus; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
