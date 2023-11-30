package com.javalab.controller.scheduler;

import com.javalab.exceptions.PropertiesFileNotFoundException;
import com.javalab.model.Task;
import com.javalab.view.NotificationView;

import java.util.Timer;
import java.util.TimerTask;

public class TaskThread extends TimerTask {
    private Timer timer;
    private Task task;

    public TaskThread(Task task) {
        this.task = task;
        this.timer = new Timer();
    }

    public void startTimer() {
        this.timer.schedule(this, task.getDate());
    }

    public void cancelTimer() {
        this.timer.cancel();
    }

    @Override
    public void run() {
//        Scanner scanner = new Scanner(System.in);
//        Controller controller = null;
//        try {
//            controller = Controller.getController();
//        } catch (PropertiesFileNotFoundException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Нотификация для задачи: " + task);
//        System.out.println("1. Выполнена");
//        System.out.println("2. Не выполнена");
//        System.out.println("3. Не выполнена, перенести");
//        System.out.print("Пункт: ");
//        String action = scanner.nextLine();
//        int parsedAction = Integer.valueOf(action);
//
//        switch (parsedAction) {
//            case 1:
//                try {
//                    this.task.setTaskStatus(Status.COMPLETED);
//                    controller.updateTask(task);
//                } catch (TaskNotFoundException e) {  //todo
//                    e.printStackTrace();
//                }
//                break;
//            case 2:
//                try {
//                    this.task.setTaskStatus(Status.CANCELED);
//                    controller.updateTask(task);
//                } catch (TaskNotFoundException e) {  //todo
//                    e.printStackTrace();
//                }
//                break;
//            case 3:
//                System.out.println("На какое время перенести?");
//                System.out.println("1. На 10 минут");
//                System.out.println("2. На один час");
//                System.out.println("3. На один день");
//                System.out.println("4. Другую дату");
//                System.out.print("Пункт: ");
//                String id5 = scanner.nextLine();
//                int id6 = Integer.valueOf(id5);
//                //todo
//        }
//        System.out.println(task);
//        NotificationView notificationView = new NotificationView();
////        notificationView.setTask(task);
//        notificationView.runNotificationView();

//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Window1.fxml"));
//        Stage stage = new Stage();
//        ControllerWindow1 controllerWindow1 = fxmlLoader.getController();
//        controllerWindow1.setTask(task);
////        ControllerWindow1 controllerWindow1= fxmlLoader.getController();
////        controllerWindow1.setTask(task);
////        NotificationView notificationView=fxmlLoader.setClassLoader();
//        notificationView.setTask(task);
//        notificationView.runNotificationView();


//        ControllerWindow1 controllerWindow1=new ControllerWindow1();
//        controllerWindow1.setTask(task);
//        controllerWindow1.aaa();
//        NotificationView notificationView = new NotificationView();
//        notificationView.runNotificationView();


//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Window1.fxml"));
//        try {
//            Parent root = fxmlLoader.load();
//            ControllerWindow1 controllerWindow1 = fxmlLoader.getController();
//            controllerWindow1.setTask(task);
//            controllerWindow1.aaa();
////            Stage stage = new Stage();
////            stage.setScene(new Scene(root));
//            NotificationView notificationView = new NotificationView();
//        notificationView.runNotificationView();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        Parent root=loader.getRoot();
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root));

//        Handler handler = new Handler();
//        try {
//            handler.showNotification();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Window1.fxml"));
//        ControllerWindow1 controllerWindow1 = fxmlLoader.getController();
//        controllerWindow1.setTask(task);
//        controllerWindow1.aaa();
////        controllerWindow1.setLabel();
//        NotificationView2 notificationView2 = new NotificationView2();
//        notificationView2.runNotificationView();

//        ControllerWindow1 controllerWindow1=new ControllerWindow1();
//        controllerWindow1.setTask(task);
//        controllerWindow1.aaa();
//        controllerWindow1.setLabel();
//        NotificationView2 notificationView2 = new NotificationView2();
//        notificationView2.runNotificationView();

        System.out.println("в тасктред до создания нотифвиев" + task);
        NotificationView notificationView = new NotificationView(task);
        try {
            notificationView.runNotificationView();
            System.out.println("в тасктред ПОСЛЕ создания нотифвиев" + task);
        } catch (PropertiesFileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
