package com.javalab;


import com.javalab.controller.Controller;
import com.javalab.exceptions.PropertiesFileNotFoundException;
import com.javalab.model.Task;
import com.javalab.view.Handler;
import com.javalab.view.NotificationView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Date;


//public class TestMain extends Application {
public class TestMain  {

    public static void main(String[] args) throws PropertiesFileNotFoundException {
//
//        Controller controller=Controller.getController();
//        controller.createTask("aaggggga", "bbbb", new Date(System.currentTimeMillis()));
//        System.out.println(controller.getAllTasks());
        NotificationView notificationView=new NotificationView();
        notificationView.runNotificationView();
//        Handler handler = new Handler();
//        handler.handlerStart();
//        Application.launch(args);
    }

//    @Override
//    public void start(Stage stage) throws Exception {
//        Handler testHandler = new Handler();
//        testHandler.startHandler(stage);
//    }

}


