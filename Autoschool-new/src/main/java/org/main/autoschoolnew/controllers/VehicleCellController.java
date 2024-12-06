package org.main.autoschoolnew.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.main.autoschoolnew.models.Vehicle;

public class VehicleCellController {

    @FXML
    private ImageView ImageViewVehiclePhoto;
    @FXML
    private Label LabelVehicleModel;
    @FXML
    private Label LabelVehicleType;
    @FXML
    private Label LabelRegistrationNumber;

    private VBox root;

    public void setData(Vehicle vehicle) {
        // Устанавливаем данные для транспортного средства
        LabelVehicleModel.setText("Модель: " + vehicle.getVehicleModel());
        LabelVehicleType.setText("Тип: " + vehicle.getVehicleType().getTypeName());
        LabelRegistrationNumber.setText("Рег. номер: " + vehicle.getRegistrationNumber());


    }
}
