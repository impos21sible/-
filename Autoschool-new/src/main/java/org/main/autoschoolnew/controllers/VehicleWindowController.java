package org.main.autoschoolnew.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.main.autoschoolnew.cell_controllers.VehicleCellController;
import org.main.autoschoolnew.models.Vehicle;
import org.main.autoschoolnew.service.VehicleService;
import org.main.autoschoolnew.util.Manager;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class VehicleWindowController implements Initializable {

    @FXML
    private Label LabelVehicle;
    @FXML
    private TextField TextFieldSearchVehicle;
    @FXML
    private ComboBox<String> ComboboxSortVehicle;
    @FXML
    private ListView<Vehicle> ListViewVehicles;

    private VehicleService vehicleService = new VehicleService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LabelVehicle.setText("Добро пожаловать, " + Manager.currentInstructor.getFirstName());

        ComboboxSortVehicle.setItems(FXCollections.observableArrayList("Модель", "Тип", "Номер"));
        ComboboxSortVehicle.setValue("Модель");

        TextFieldSearchVehicle.textProperty().addListener((observable, oldValue, newValue) -> filterVehicles(newValue));

        loadVehicles();
    }

    public void loadVehicles() {
        List<Vehicle> vehicles = vehicleService.findAll();

        // Сортировка
        String selectedSort = ComboboxSortVehicle.getValue();
        if (selectedSort != null) {
            Comparator<Vehicle> comparator = getComparator(selectedSort);
            vehicles.sort(comparator);
        }

        setListView(vehicles);
    }

    private Comparator<Vehicle> getComparator(String selectedSort) {
        switch (selectedSort) {
            case "Модель":
                return Comparator.comparing(Vehicle::getVehicleModel);
            case "Тип":
                return Comparator.comparing(vehicle -> vehicle.getVehicleType().getTypeName());
            case "Номер":
                return Comparator.comparing(Vehicle::getRegistrationNumber);
            default:
                return Comparator.comparing(Vehicle::getIdVehicle);
        }
    }

    private void setListView(List<Vehicle> vehicles) {
        ListViewVehicles.setCellFactory(listView -> {
            return new ListCell<Vehicle>() {
                @Override
                protected void updateItem(Vehicle vehicle, boolean empty) {
                    super.updateItem(vehicle, empty);
                    if (empty || vehicle == null) {
                        setGraphic(null);
                    } else {
                        // Создание новой ячейки для каждого транспортного средства
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/main/autoschoolnew/vehicles-cell-view.fxml"));
                        try {
                            HBox hbox = loader.load();
                            VehicleCellController controller = loader.getController();
                            controller.setData(vehicle); // Передаем данные в контроллер ячейки
                            setGraphic(hbox);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        });

        ListViewVehicles.setItems(FXCollections.observableArrayList(vehicles));
    }


    private void filterVehicles(String searchQuery) {
        List<Vehicle> vehicles = vehicleService.findAll();

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            vehicles = vehicles.stream()
                    .filter(vehicle -> vehicle.getVehicleModel().toLowerCase().contains(searchQuery.toLowerCase()))
                    .collect(Collectors.toList());
        }

        String selectedSort = ComboboxSortVehicle.getValue();
        if (selectedSort != null) {
            Comparator<Vehicle> comparator = getComparator(selectedSort);
            vehicles.sort(comparator);
        }

        setListView(vehicles);
    }

    @FXML
    public void BtnGoToStudentsAction(ActionEvent actionEvent) {
        // Получаем текущую стадию (окно, на котором был клик по кнопке)
        Stage currentStage = (Stage) ((javafx.scene.control.Button) actionEvent.getSource()).getScene().getWindow();

        // Загружаем новый сцену (главная страница студентов)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/main/autoschoolnew/main-view.fxml"));
        Parent root = null;
        try {
            root = loader.load();  // Загружаем FXML
        } catch (IOException e) {
            e.printStackTrace();  // В случае ошибки выводим стек исключений
        }

        // Создаем новый объект Scene с загруженным root
        Scene scene = new Scene(root);
        scene.getStylesheets().add("base-styles.css");  // Добавляем стили

        // Создаем новое окно (Stage) для страницы студентов
        Stage studentStage = new Stage();
        studentStage.setTitle("Student Page");  // Устанавливаем заголовок
        studentStage.setScene(scene);  // Устанавливаем сцену
        studentStage.setWidth(1000);   // Ширина нового окна
        studentStage.setHeight(600);   // Высота нового окна

        // Обрабатываем закрытие нового окна
        studentStage.setOnCloseRequest(e -> {
            currentStage.show();  // При закрытии нового окна показываем старое
        });

        // Прячем текущее окно (которое содержит кнопку)
        currentStage.hide();

        // Показываем новое окно
        studentStage.show();
    }

    public void ComboboxSortVehicleAction(ActionEvent actionEvent) {
        loadVehicles();
    }


}
