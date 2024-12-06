package org.main.autoschoolnew.util;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.main.autoschoolnew.models.Instructor;

import java.io.IOException;
import java.util.Optional;

public class Manager {
    public static Instructor currentInstructor = null;
    public static Stage mainStage;

    public static void ShowPopup() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Закрыть приложение");
        alert.setHeaderText("Вы хотите выйти из приложения?");
        alert.setContentText("Все несохраненные данные, будут утеряны");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    public static void ShowErrorMessageBox(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public static void changeScene(String fxmlFile, boolean isNewWindow) {
        try {
            // Загружаем FXML файл
            Parent root = FXMLLoader.load(Manager.class.getResource("/org/main/autoschoolnew/" + fxmlFile));

            if (isNewWindow) {
                // Создаем новое окно
                Stage newStage = new Stage();
                Scene scene = new Scene(root);
                scene.getStylesheets().add("base-styles.css");  // Добавляем стили, если нужно
                newStage.setScene(scene);
                newStage.setTitle("Новая сцена");
                newStage.setWidth(1000); // ширина окна
                newStage.setHeight(600); // высота окна

                // Обрабатываем закрытие нового окна
                newStage.setOnCloseRequest(e -> {
                    mainStage.show();  // Показываем старое окно при закрытии нового
                });

                // Прячем текущее окно (если оно не null)
                if (mainStage != null) {
                    mainStage.hide();
                }

                newStage.show();
            } else {
                // Если не нужно новое окно, просто меняем сцену в текущем окне
                mainStage.setScene(new Scene(root));
                mainStage.show(); // Показываем старое окно с новой сценой
            }
        } catch (IOException e) {
            ShowErrorMessageBox("Ошибка загрузки интерфейса: " + e.getMessage());
        }
    }

}
