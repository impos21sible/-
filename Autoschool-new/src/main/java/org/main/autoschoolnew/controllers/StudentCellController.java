package org.main.autoschoolnew.controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.main.autoschoolnew.models.Student;

public class StudentCellController {

    @FXML
    private ImageView ImageViewPhoto;
    @FXML
    private Label LabelName;
    @FXML
    private Label LabelInstructor;
    @FXML
    private Label LabelCategory;
    @FXML
    private Label LabelStatus;

    private VBox root;

    public void setData(Student student) {
        // Устанавливаем данные для студента
        LabelName.setText(student.getFullName());
        LabelInstructor.setText(student.getInstructor() != null ? "Инструктор: " + student.getInstructor().getFullName() : "Инструктор: Не назначен");
        LabelCategory.setText("Категория: " + student.getCategory().getName());
        LabelStatus.setText("Статус: " + student.getStatusName());

    }

    public VBox getRoot() {
        return root;
    }
}
