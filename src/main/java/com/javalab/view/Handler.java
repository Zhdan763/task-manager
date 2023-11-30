package com.javalab.view;

import com.javalab.controller.Controller;
import com.javalab.exceptions.PropertiesFileNotFoundException;
import com.javalab.exceptions.TaskNotFoundException;
import com.javalab.model.Task;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Date;

import static javafx.application.Platform.runLater;

public class Handler {
private Task task;



//    public void startHandler(Stage stage) throws IOException, PropertiesFileNotFoundException, TaskNotFoundException {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Window1.fxml"));
//        Parent root = fxmlLoader.load();
//        stage.setScene(new Scene(root, 600, 500));
//      ControllerWindow1  controllerWindow1=fxmlLoader.<ControllerWindow1>getController();
////        ControllerWindow1 controllerWindow1 = fxmlLoader.getController();
////        Controller controller = Controller.getController();
////        controller.createTask("aaggggga", "bbbb", new Date(System.currentTimeMillis()));
////        controllerWindow1.setTask(controller.getTask(0));
//        System.out.println("до сэта "+ task);
//        controllerWindow1.setTask(task);
//        System.out.println("после сета "+task);
////        System.out.println(controller.getAllTasks());
////        System.out.println(controller.getTask(0).getDate());
//
////        controllerWindow1.setLabel();
//        stage.show();
//    }

public void handlerStart()  {
//    runLater(() -> {
//        try {
//            showNotificationView();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    });
    try {
        showNotificationView();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private Stage createStage() throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Window1.fxml"));
    Stage stage = new Stage();
    stage.setScene(new Scene(fxmlLoader.load()));
    ControllerWindow1 controllerWindow1 = fxmlLoader.getController();
    controllerWindow1.setTask(task);
    return stage;
}

private void showNotificationView() throws Exception {
    NotificationView notificationView = new NotificationView();
    notificationView.start(createStage());
    notificationView.runNotificationView();
}

}
