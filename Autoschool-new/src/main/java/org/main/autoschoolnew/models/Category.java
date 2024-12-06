package org.main.autoschoolnew.models;

import jakarta.persistence.*;

@Entity
@Table(name = "categories", schema = "autoschool")
public class Category {

    @Id
    @Column(name = "id_category")
    private Integer idCategory;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Override
    public String toString() {
        return name; // Возвращает имя категории вместо стандартного хеш-кода
    }

    // Getters and setters
    public Integer getIdCategory() { return idCategory; }
    public void setIdCategory(Integer idCategory) { this.idCategory = idCategory; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
