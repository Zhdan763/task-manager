package com.javalab.view;

import com.javalab.TestMain;
import com.javalab.controller.Controller;
import com.javalab.exceptions.PropertiesFileNotFoundException;
import com.javalab.model.Task;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class NotificationView extends Application {
private Task task;
//private Stage stage;

    public void runNotificationView() throws PropertiesFileNotFoundException {
        System.out.println("in runNitif" + task);


        Application.launch();
        System.out.println("in runNitif" + task);
    }

    public NotificationView() {
    }

    public NotificationView(Task task) {
        this.task = task;
    }

    @Override
    public void start(Stage stage) throws Exception {
//        this.stage=stage;
//        stage.show();
//
//
//        Platform.runLater(() -> {
//            try {
//                showControllerWindow1();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//        });


//        Handler testHandler = new Handler();
//        testHandler.startHandler(stage);
////stage.show();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Window1.fxml"));
        Parent root = fxmlLoader.load();

        stage.setScene(new Scene(root, 600, 500));
////      ControllerWindow1  controllerWindow1=fxmlLoader.<ControllerWindow1>getController();
        ControllerWindow1 controllerWindow1 = fxmlLoader.getController();
        Controller controller = Controller.getController();
        controller.createTask("aaggggga", "bbbb", new Date(System.currentTimeMillis()));
        System.out.println("до сэта "+ controller.getTask(0));
        System.out.println(controllerWindow1);
        controllerWindow1.setTask(controller.getTask(0));
//        controllerWindow1.setTask(task);
        System.out.println("после сета "+controller.getTask(0));
//////        System.out.println(controller.getAllTasks());
//////        System.out.println(controller.getTask(0).getDate());

        controllerWindow1.setLabel();
        stage.show();

    }


//    public void showControllerWindow1 () throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Window1.fxml"));
//        Parent root = fxmlLoader.load();
//        stage.setScene(new Scene(root, 600, 500));
////        ControllerWindow1 controllerWindow1 = fxmlLoader.getController();
//        ControllerWindow1 controllerWindow1 = fxmlLoader.getController();
//        System.out.println("до сэта "+ task);
//        controllerWindow1.setTask(task);
//        System.out.println("после сета "+task);
//        stage.show();
//    }
}
