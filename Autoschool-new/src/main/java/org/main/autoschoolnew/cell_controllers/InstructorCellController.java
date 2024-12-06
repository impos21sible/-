package org.main.autoschoolnew.cell_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.main.autoschoolnew.models.Instructor;

public class InstructorCellController {

    @FXML
    private ImageView ImageViewPhoto;
    @FXML
    private Label LabelFullName;
    @FXML
    private Label LabelCategory;
    @FXML
    private Label LabelExperience;
    @FXML
    private Label LabelPhone;

    private VBox root;



    public void setData(Instructor instructor) {
        // Устанавливаем данные для инструктора
        LabelFullName.setText(instructor.getFullName());
        LabelCategory.setText("Категория: " + instructor.getCategories());
        LabelExperience.setText("Стаж: " + instructor.getExperience() + " лет");
        LabelPhone.setText("Телефон: " + instructor.getPhone());

    }
}
