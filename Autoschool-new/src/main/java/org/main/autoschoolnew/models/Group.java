package org.main.autoschoolnew.models;

import jakarta.persistence.*;

@Entity
@Table(name = "groups", schema = "autoschool")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group")
    private Integer idGroup;

    // Используем Integer для groupNumber, чтобы поддерживать возможность NULL
    @Column(name = "group_number")
    private Integer groupNumber;

    @Column(name = "group_name", length = 100, nullable = false)
    private String groupName;

    @ManyToOne
    @JoinColumn(name = "id_instructor", nullable = false)
    private Instructor instructor;

    // Getters and setters
    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    // Методы для получения имени группы и имени инструктора
    public String getName() {
        return groupName;
    }

    public String getInstructorName() {
        return instructor != null ? instructor.getName() : "Нет инструктора";
    }

    public Integer getGroupNumber() {  // Теперь это Integer для поддержки null
        return groupNumber;
    }

    public void setGroupNumber(Integer groupNumber) {  // Принимаем Integer
        this.groupNumber = groupNumber;
    }
}
