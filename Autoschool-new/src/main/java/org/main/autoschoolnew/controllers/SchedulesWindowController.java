package org.main.autoschoolnew.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.main.autoschoolnew.models.Schedule;
import org.main.autoschoolnew.service.ScheduleService;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SchedulesWindowController {

    @FXML
    private TableView<Schedule> TableViewSchedules;
    @FXML
    private TableColumn<Schedule, String> columnGroup;
    @FXML
    private TableColumn<Schedule, String> columnDate;
    @FXML
    private TableColumn<Schedule, String> columnTime;
    @FXML
    private TableColumn<Schedule, String> columnInstructor;
    @FXML
    private TextField TextFieldSearchSchedule;
    @FXML
    private ComboBox<String> ComboboxSortSchedule;

    private final ScheduleService scheduleService = new ScheduleService();

    public Button BtnGoToStudent;

    // Метод для загрузки всех расписаний в TableView
    public void loadSchedules() {
        List<Schedule> schedules = scheduleService.findAll();
        ObservableList<Schedule> observableSchedules = FXCollections.observableArrayList(schedules);
        TableViewSchedules.setItems(observableSchedules);
    }

    @FXML
    public void handleSearchAction() {
        String searchText = TextFieldSearchSchedule.getText().trim();

        if (!searchText.isEmpty()) {
            // Фильтруем по строковому значению имени группы
            List<Schedule> filteredSchedules = scheduleService.findAll().stream()
                    .filter(schedule -> schedule.getGroup() != null &&
                            schedule.getGroup().getGroupName() != null &&
                            schedule.getGroup().getGroupName().toLowerCase().contains(searchText.toLowerCase()))  // Сравниваем имена группы
                    .collect(Collectors.toList());

            ObservableList<Schedule> observableSchedules = FXCollections.observableArrayList(filteredSchedules);
            TableViewSchedules.setItems(observableSchedules);
        } else {
            // Если текст пустой, загружаем все расписания
            loadSchedules();
        }
    }


    // Метод для сортировки расписания
    public void sortSchedules(String sortBy) {
        List<Schedule> schedules = scheduleService.findAll();

        switch (sortBy) {
            case "Дата":
                schedules.sort(Comparator.comparing(Schedule::getLessonDate));  // Сортировка по дате
                break;
            case "Время":
                schedules.sort(Comparator.comparing(Schedule::getLessonTime));  // Сортировка по времени
                break;
            case "Группа":  // Сортировка по числовому значению группы
                schedules.sort(Comparator.comparing(schedule -> {
                    if (schedule.getGroup() != null) {
                        return schedule.getGroup().getGroupNumber();  // Используем groupNumber для сортировки
                    } else {
                        return Integer.MAX_VALUE;  // Если группа отсутствует, ставим в конец
                    }
                }));
                break;
            default:
                break;
        }

        // Обновление таблицы с отсортированными расписаниями
        ObservableList<Schedule> observableSchedules = FXCollections.observableArrayList(schedules);
        TableViewSchedules.setItems(observableSchedules);
    }



    // Метод для обработки сортировки по расписанию через ComboBox
    @FXML
    public void ComboboxSortScheduleAction(ActionEvent actionEvent) {
        String sortBy = ComboboxSortSchedule.getValue();
        sortSchedules(sortBy);
    }

    // Инициализация (вызовите загрузку расписания)
    @FXML
    public void initialize() {
        loadSchedules();
        columnGroup.setCellValueFactory(cellData -> cellData.getValue().groupProperty());
        columnDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        columnTime.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        columnInstructor.setCellValueFactory(cellData -> cellData.getValue().instructorProperty());

        // Добавление элементов в ComboBox
        ComboboxSortSchedule.setItems(FXCollections.observableArrayList("Дата", "Время", "Группа"));  // Включили "Группа"
    }

    @FXML
    public void BtnGoToStudentAction(ActionEvent actionEvent) {
        // Получаем текущую стадию (окно, на котором был клик по кнопке)
        Stage currentStage = (Stage) BtnGoToStudent.getScene().getWindow();

        // Загружаем новый сцену (вид инструкторов)
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
}
