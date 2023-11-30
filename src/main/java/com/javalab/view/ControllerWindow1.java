package com.javalab.view;

import com.javalab.controller.Controller;
import com.javalab.exceptions.PropertiesFileNotFoundException;
import com.javalab.exceptions.TaskNotFoundException;
import com.javalab.model.Status;
import com.javalab.model.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class ControllerWindow1 {
    private Task task;

    public ControllerWindow1() {
    }

//    public ControllerWindow1(Task task) {
//        this.task = task;
//    }

    public void setTask(Task task) {
        this.task = task;
//        Platform.runLater(() -> {
//            this.task = task;
//        });

    }

    public void aaa() {
        System.out.println("test in controller " + task);

    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button canceled;

    @FXML
    private Button completed;

    @FXML
    private Label notificationTask;

    @FXML
    private Button overdue;

    @FXML
    private Label tableTaskName;

    @FXML
    private Label tableDescription;

    @FXML
    private Label tableDate;

    @FXML
    private Label tableId;

    @FXML
    private Label tableTaskStatus;


    @FXML
    void completed(ActionEvent event) throws PropertiesFileNotFoundException, TaskNotFoundException {
        task.setTaskStatus(Status.COMPLETED);
        Controller controller = Controller.getController();
        controller.updateTask(task);
        System.out.println(task);
    }

    @FXML
    void initialize() throws PropertiesFileNotFoundException {
        overdue.setOnAction(actionEvent -> {
            overdue.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Window2.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            ControllerWindow2 controllerWindow2 = loader.getController();
            controllerWindow2.setTask(task);
            stage.showAndWait();
        });

    }

    @FXML
    void noDone(ActionEvent event) throws PropertiesFileNotFoundException, TaskNotFoundException {
        task.setTaskStatus(Status.CANCELED);
        Controller controller = Controller.getController();
        controller.updateTask(task);
        System.out.println(task);

    }

    public void setLabel() {


        tableTaskName.setText(task.getTaskName());
        tableDescription.setText(task.getDescription());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateToString = simpleDateFormat.format(task.getDate());
        tableDate.setText(dateToString);

        String id = Integer.toString(task.getId());
        tableId.setText(id);

        String status = (task.getTaskStatus()).toString();
        tableTaskStatus.setText(status);

//        tableTaskStatus.setText(task.getTaskStatus());
    }


}
