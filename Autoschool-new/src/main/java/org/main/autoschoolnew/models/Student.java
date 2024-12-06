package org.main.autoschoolnew.models;

import jakarta.persistence.*;

@Entity
@Table(name = "students", schema = "autoschool")
public class Student {

    @Override
    public String toString() {
        return firstName + " " + lastName;  // Это строка, которая будет отображаться в ComboBox
    }

    @Id
    @Column(name = "id_student")
    private Integer idStudent;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "middle_name", length = 50)
    private String middleName;

    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id_category", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_vehicle", referencedColumnName = "id_vehicle", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "id_instructor", referencedColumnName = "id_instructor", nullable = true)
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "id_statuses", referencedColumnName = "id_status", nullable = false)
    private StudentStatus status;

    // Getter and setter for `status`
    public StudentStatus getStatusEntity() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    // Method to get status name
    public String getStatusName() {
        return status != null ? status.getName() : "Unknown";
    }

    // Getters and setters for other fields
    public Integer getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Integer idStudent) {
        this.idStudent = idStudent;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    // Full name method
    public String getFullName() {
        return firstName + " " + (middleName != null ? middleName + " " : "") + lastName;
    }
}
