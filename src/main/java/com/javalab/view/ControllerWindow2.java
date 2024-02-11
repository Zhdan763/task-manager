package com.javalab.view;


import com.javalab.controller.Controller;
import com.javalab.exceptions.PropertiesFileNotFoundException;
import com.javalab.exceptions.TaskNotFoundException;
import com.javalab.exceptions.TaskSchedulerException;
import com.javalab.model.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class ControllerWindow2 {

    private Task task;

    public void setTask(Task task) {
        this.task = task;
    }


    @FXML
    private DatePicker calendar;

    @FXML
    private TextField textHour;

    @FXML
    private TextField textMinutes;




    @FXML
    void OnThe1hour(ActionEvent event) {
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(task.getDate());
        calendar.add(Calendar.HOUR, 1);
        Date newDate=calendar.getTime();
        task.setDate(newDate);
        System.out.println(task.getDate());
    }

    @FXML
    void onThe10Minutes(ActionEvent event) {
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(task.getDate());
        calendar.add(Calendar.MINUTE, 10);
        Date newDate=calendar.getTime();
        task.setDate(newDate);
        System.out.println(task.getDate());
    }

    @FXML
    void onThe1Day(ActionEvent event) {
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(task.getDate());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date newDate=calendar.getTime();
        task.setDate(newDate);
        System.out.println(task.getDate());
    }

    @FXML
    void onAnotherVariant(ActionEvent event) throws PropertiesFileNotFoundException, TaskNotFoundException, TaskSchedulerException {
        System.out.println("before "+task);
        String hoursString = textHour.getText();
        int hours=Integer.parseInt(hoursString);
        String minutesString=textMinutes.getText();
        int minutes=Integer.parseInt(minutesString);
        LocalDate localDate=calendar.getValue();
        ZoneId defaultZoneId=ZoneId.systemDefault();
        Instant instant =localDate.atStartOfDay(defaultZoneId).toInstant();
        Date date=Date.from(instant);
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hours);
        calendar.add(Calendar.MINUTE, minutes);
        Date newDate=calendar.getTime();
        task.setDate(newDate);
        Controller controller = Controller.getController();
        controller.updateTask(task);
        System.out.println("after "+task);
    }

    public void test () {
        System.out.println("я из контроллера 2 хай");
        System.out.println(task);
    }




}

