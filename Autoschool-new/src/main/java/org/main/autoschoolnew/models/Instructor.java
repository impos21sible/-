package org.main.autoschoolnew.models;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "instructors", schema = "autoschool")
public class Instructor {

    @Id
    @Column(name = "id_instructor")
    private Integer idInstructor;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Column(name = "phone", length = 12)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "id_experience")
    private Integer idExperience;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_experience", referencedColumnName = "id_experience", insertable = false, updatable = false)
    private Experience experience;

    @ManyToMany
    @JoinTable(
            name = "instructor_categories",
            schema = "autoschool",
            joinColumns = @JoinColumn(name = "id_instructor"),
            inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    private Set<Category> categories;

    @Override
    public String toString() {
        return firstName + " " + lastName;  // Это строка, которая будет отображаться в ComboBox
    }

    // Getters and Setters
    public Integer getIdInstructor() {
        return idInstructor;
    }

    public void setIdInstructor(Integer idInstructor) {
        this.idInstructor = idInstructor;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdExperience() {
        return idExperience;
    }

    public void setIdExperience(Integer idExperience) {
        this.idExperience = idExperience;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Новый метод для получения полного имени
    public String getFullName() {
        return String.format("%s %s %s",
                lastName != null ? lastName : "",
                firstName != null ? firstName : "",
                middleName != null ? middleName : "").trim();
    }

    // Новый метод для получения опыта
    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    // Новый метод для получения категорий
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    // Метод для получения имени инструктора
    public String getName() {
        return getFullName();
    }
}
