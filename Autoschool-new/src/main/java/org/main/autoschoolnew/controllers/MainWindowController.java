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
import org.main.autoschoolnew.cell_controllers.StudentCellController;
import org.main.autoschoolnew.models.Student;
import org.main.autoschoolnew.models.Instructor;
import org.main.autoschoolnew.service.StudentService;
import org.main.autoschoolnew.util.Manager;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainWindowController implements Initializable {


    private final StudentService studentService = new StudentService();
    public TextField TextFieldSearchLastName;
    public Button BtnGoToInstructors;
    public Button BtnBack;


    @FXML
    private ListView<Student> ListViewStudents;

    @FXML
    private ComboBox<String> ComboboxSort;
    @FXML
    private Label LabelUser;


    @FXML
    void BtnBackAction(javafx.event.ActionEvent event) {
        // Получаем текущее окно (Stage)
        Stage currentStage = (Stage) BtnBack.getScene().getWindow();

        // Закрываем текущее окно
        currentStage.close();

        // Загружаем новое окно (login-view.fxml)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/main/autoschoolnew/login-view.fxml"));
        Parent root = null;
        try {
            root = loader.load();  // Загружаем FXML
        } catch (IOException e) {
            e.printStackTrace();  // В случае ошибки выводим стек исключений
        }

        // Создаем новый объект Scene с загруженным root
        Scene scene = new Scene(root);
        scene.getStylesheets().add("base-styles.css");  // Добавляем стили (если нужны)

        // Создаем новое окно (Stage) для страницы авторизации
        Stage loginStage = new Stage();
        loginStage.setTitle("Авторизация");  // Устанавливаем заголовок
        loginStage.setScene(scene);  // Устанавливаем сцену
        loginStage.setWidth(400);   // Ширина нового окна
        loginStage.setHeight(250);   // Высота нового окна

        // Показываем новое окно
        loginStage.show();
    }


    @FXML
    void ComboboxSortAction(javafx.event.ActionEvent event) {
        String selectedSort = ComboboxSort.getValue();
        if (selectedSort != null) {
            Comparator<Student> comparator;

            switch (selectedSort) {
                case "Имя":
                    comparator = Comparator.comparing(Student::getFullName);
                    break;
                case "Категория":
                    comparator = Comparator.comparing(student -> student.getCategory().getName());
                    break;
                case "Статус":
                    comparator = Comparator.comparing(Student::getStatusName);
                    break;
                default:
                    comparator = Comparator.comparing(Student::getIdStudent);
            }

            List<Student> sortedStudents = ListViewStudents.getItems()
                    .stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());

            ListViewStudents.setItems(FXCollections.observableArrayList(sortedStudents));
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LabelUser.setText("Добро пожаловать, " + Manager.currentInstructor.getFirstName());

        // Устанавливаем параметры сортировки
        ComboboxSort.setItems(FXCollections.observableArrayList("Имя", "Категория", "Статус"));
        ComboboxSort.setValue("Имя"); // Устанавливаем значение по умолчанию

        // Добавляем слушатель для поля поиска
        TextFieldSearchLastName.textProperty().addListener((observable, oldValue, newValue) -> {
            filterStudents(newValue);
        });

        // Загружаем всех студентов
        loadStudents(null);
    }


    private void filterStudents(String searchQuery) {
        // Загружаем полный список студентов
        List<Student> students = studentService.findAll();

        // Фильтруем по фамилии
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            students = students.stream()
                    .filter(student -> student.getLastName().toLowerCase().contains(searchQuery.toLowerCase()))
                    .collect(Collectors.toList());
        }

        // Сортируем, если выбрана сортировка
        String selectedSort = ComboboxSort.getValue();
        if (selectedSort != null) {
            Comparator<Student> comparator;
            switch (selectedSort) {
                case "Имя":
                    comparator = Comparator.comparing(Student::getFullName);
                    break;
                case "Категория":
                    comparator = Comparator.comparing(student -> student.getCategory().getName());
                    break;
                case "Статус":
                    comparator = Comparator.comparing(Student::getStatusName);
                    break;
                default:
                    comparator = Comparator.comparing(Student::getIdStudent);
            }
            students.sort(comparator);
        }

        // Обновляем ListView
        ListViewStudents.setItems(FXCollections.observableArrayList(students));
        ListViewStudents.refresh();
    }


    public void loadStudents(Instructor instructor) {
        ListViewStudents.getItems().clear();

        List<Student> students = studentService.findAll();

        if (instructor != null) {
            students = students.stream()
                    .filter(student -> student.getInstructor() != null && student.getInstructor().equals(instructor))
                    .collect(Collectors.toList());
        }

        String selectedSort = ComboboxSort.getValue();
        if (selectedSort != null) {
            Comparator<Student> comparator;
            switch (selectedSort) {
                case "Имя":
                    comparator = Comparator.comparing(Student::getFullName);
                    break;
                case "Категория":
                    comparator = Comparator.comparing(student -> student.getCategory().getName());
                    break;
                case "Статус":
                    comparator = Comparator.comparing(Student::getStatusName);
                    break;
                default:
                    comparator = Comparator.comparing(Student::getIdStudent);
            }
            students.sort(comparator);
        }

        ListViewStudents.setCellFactory(listView -> new ListCell<Student>() {
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);
                if (empty || student == null) {
                    setGraphic(null);
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/main/autoschoolnew/student-cell.fxml"));
                    try {
                        HBox root = loader.load();
                        StudentCellController controller = loader.getController();
                        controller.setData(student);
                        setGraphic(root);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        ListViewStudents.setItems(FXCollections.observableArrayList(students));
    }

    @FXML
    public void BtnGoToInstructorsAction(ActionEvent actionEvent) {
        // Получаем текущую стадию (окно, на котором был клик по кнопке)
        Stage currentStage = (Stage) BtnGoToInstructors.getScene().getWindow();

        // Загружаем новый сцену (вид инструкторов)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/main/autoschoolnew/instructors-view.fxml"));
        Parent root = null;
        try {
            root = loader.load();  // Загружаем FXML
        } catch (IOException e) {
            e.printStackTrace();  // В случае ошибки выводим стек исключений
        }

        // Создаем новый объект Scene с загруженным root
        Scene scene = new Scene(root);
        scene.getStylesheets().add("base-styles.css");  // Добавляем стили

        // Создаем новое окно (Stage) для страницы инструкторов
        Stage instructorStage = new Stage();
        instructorStage.setTitle("Instructor Page");  // Устанавливаем заголовок
        instructorStage.setScene(scene);  // Устанавливаем сцену
        instructorStage.setWidth(1000);   // Ширина нового окна
        instructorStage.setHeight(600);   // Высота нового окна

        // Обрабатываем закрытие нового окна
        instructorStage.setOnCloseRequest(e -> {
            currentStage.show();  // При закрытии нового окна показываем старое
        });

        // Прячем текущее окно (которое содержит кнопку)
        currentStage.hide();

        // Показываем новое окно
        instructorStage.show();
    }


    public void BtnGoToVehiclesAction(ActionEvent actionEvent) {
        // Получаем текущую стадию (окно, на котором был клик по кнопке)
        Stage currentStage = (Stage) BtnGoToInstructors.getScene().getWindow();

        // Загружаем новый сцену (вид инструкторов)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/main/autoschoolnew/vehicles-view.fxml"));
        Parent root = null;
        try {
            root = loader.load();  // Загружаем FXML
        } catch (IOException e) {
            e.printStackTrace();  // В случае ошибки выводим стек исключений
        }

        // Создаем новый объект Scene с загруженным root
        Scene scene = new Scene(root);
        scene.getStylesheets().add("base-styles.css");  // Добавляем стили

        // Создаем новое окно (Stage) для страницы инструкторов
        Stage instructorStage = new Stage();
        instructorStage.setTitle("Vehicles Page");  // Устанавливаем заголовок
        instructorStage.setScene(scene);  // Устанавливаем сцену
        instructorStage.setWidth(1000);   // Ширина нового окна
        instructorStage.setHeight(600);   // Высота нового окна

        // Обрабатываем закрытие нового окна
        instructorStage.setOnCloseRequest(e -> {
            currentStage.show();  // При закрытии нового окна показываем старое
        });

        // Прячем текущее окно (которое содержит кнопку)
        currentStage.hide();

        // Показываем новое окно
        instructorStage.show();
    }


    public void BtnGoToSchedulesAction(ActionEvent actionEvent) {
        // Получаем текущую стадию (окно, на котором был клик по кнопке)
        Stage currentStage = (Stage) BtnGoToInstructors.getScene().getWindow();

        // Загружаем новый сцену (вид инструкторов)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/main/autoschoolnew/schedules-view.fxml"));
        Parent root = null;
        try {
            root = loader.load();  // Загружаем FXML
        } catch (IOException e) {
            e.printStackTrace();  // В случае ошибки выводим стек исключений
        }

        // Создаем новый объект Scene с загруженным root
        Scene scene = new Scene(root);
        scene.getStylesheets().add("base-styles.css");  // Добавляем стили

        // Создаем новое окно (Stage) для страницы инструкторов
        Stage instructorStage = new Stage();
        instructorStage.setTitle("Schedules Page");  // Устанавливаем заголовок
        instructorStage.setScene(scene);  // Устанавливаем сцену
        instructorStage.setWidth(1000);   // Ширина нового окна
        instructorStage.setHeight(600);   // Высота нового окна

        // Обрабатываем закрытие нового окна
        instructorStage.setOnCloseRequest(e -> {
            currentStage.show();  // При закрытии нового окна показываем старое
        });

        // Прячем текущее окно (которое содержит кнопку)
        currentStage.hide();

        // Показываем новое окно
        instructorStage.show();

    }
}
