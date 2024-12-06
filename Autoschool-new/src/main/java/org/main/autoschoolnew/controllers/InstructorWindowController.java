package org.main.autoschoolnew.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.main.autoschoolnew.cell_controllers.InstructorCellController;
import org.main.autoschoolnew.models.Instructor;
import org.main.autoschoolnew.service.InstructorService;
import org.main.autoschoolnew.util.Manager;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class InstructorWindowController implements Initializable {

    @FXML
    private Label LabelInstructor;
    public TextField TextFieldSearchInstructor;
    @FXML
    private ComboBox<String> ComboboxSortInstructor;
    @FXML
    private ListView<Instructor> ListViewInstructors;

    // Create an instance of InstructorService
    private InstructorService instructorService = new InstructorService();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LabelInstructor.setText("Добро пожаловать, " + Manager.currentInstructor.getFirstName());

        // Устанавливаем параметры сортировки
        ComboboxSortInstructor.setItems(FXCollections.observableArrayList("Имя", "Категория", "Стаж"));
        ComboboxSortInstructor.setValue("Имя"); // Устанавливаем значение по умолчанию

        // Добавляем слушатель для поля поиска
        TextFieldSearchInstructor.textProperty().addListener((observable, oldValue, newValue) -> {
            filterInstructors(newValue);
        });

        // Загружаем всех инструкторов
        loadInstructors();
    }

    public void loadInstructors() {
        ListViewInstructors.getItems().clear();
        List<Instructor> instructors = instructorService.findAll(); // Use the instance of InstructorService

        String selectedSort = ComboboxSortInstructor.getValue();
        if (selectedSort != null) {
            Comparator<Instructor> comparator;
            switch (selectedSort) {
                case "Имя":
                    comparator = Comparator.comparing(Instructor::getFullName);
                    break;
                case "Категория":
                    comparator = Comparator.comparing(instructor -> instructor.getCategories().toString());
                    break;
                case "Стаж":
                    comparator = Comparator.comparing(instructor -> instructor.getExperience() != null ? instructor.getExperience().getYear() : 0);
                    break;
                default:
                    comparator = Comparator.comparing(Instructor::getIdInstructor);
            }
            instructors.sort(comparator);
        }

        ListViewInstructors.setCellFactory(listView -> new ListCell<Instructor>() {
            @Override
            protected void updateItem(Instructor instructor, boolean empty) {
                super.updateItem(instructor, empty);
                if (empty || instructor == null) {
                    setGraphic(null);
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/main/autoschoolnew/instructor-cell.fxml"));
                    try {
                        HBox root = loader.load();
                        InstructorCellController controller = loader.getController();
                        controller.setData(instructor);
                        setGraphic(root);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        ListViewInstructors.setItems(FXCollections.observableArrayList(instructors));
    }

    private void filterInstructors(String searchQuery) {
        // Получаем полный список инструкторов
        List<Instructor> instructors = instructorService.findAll(); // Use the instance of InstructorService

        // Фильтруем по имени
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            instructors = instructors.stream()
                    .filter(instructor -> instructor.getFullName().toLowerCase().contains(searchQuery.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Сортируем, если выбрана сортировка
        String selectedSort = ComboboxSortInstructor.getValue();
        if (selectedSort != null) {
            Comparator<Instructor> comparator;
            switch (selectedSort) {
                case "Имя":
                    comparator = Comparator.comparing(Instructor::getFullName);
                    break;
                case "Категория":
                    comparator = Comparator.comparing(instructor -> instructor.getCategories().toString());
                    break;
                case "Стаж":
                    comparator = Comparator.comparing(instructor -> instructor.getExperience() != null ? instructor.getExperience().getYear() : 0);
                    break;
                default:
                    comparator = Comparator.comparing(Instructor::getIdInstructor);
            }
            instructors.sort(comparator);
        }

        // Обновляем ListView
        ListViewInstructors.setItems(FXCollections.observableArrayList(instructors));
        ListViewInstructors.refresh();
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

    public void ComboboxSortInstructorAction(ActionEvent actionEvent) {
        // Логика для обработки сортировки, если она необходима
        loadInstructors();
    }
}
