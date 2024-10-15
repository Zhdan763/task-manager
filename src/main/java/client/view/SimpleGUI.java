package client.view;

import shared.model.Status;
import shared.model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class SimpleGUI extends JFrame {

    private Task task;
    private JLabel labelNotification = new JLabel("Notification Task");
    private JLabel labelName = new JLabel();
    private JLabel labelDescription = new JLabel();
    private JLabel labelDate = new JLabel();
    private JButton buttonCompleted = new JButton("Выполнена");
    private JButton buttonCanceled = new JButton("Не выполнена");
    private JButton buttonOverdue = new JButton("Перенести");
    private JFrame frame;

    public SimpleGUI(Task task) throws HeadlessException {
        this.task = task;
        frame = new JFrame("Task Manager");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        labelName.setText("Name: " + task.getTaskName());
        labelDescription.setText("Description: " + task.getDescription());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String dateToString = simpleDateFormat.format(task.getDate());
        labelDate.setText("Date: " + dateToString);

        frame.add(labelNotification, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(labelName, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(labelDescription, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(labelDate, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(buttonCompleted, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(buttonCanceled, new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(buttonOverdue, new GridBagConstraints(0, 6, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        buttonCompleted.addActionListener(new ButtonEventListnerCompleted());
        buttonCanceled.addActionListener(new ButtonEventListnerCanceled());
        buttonOverdue.addActionListener(new ButtonEventListnerOverdue());

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();

    }

//    public SimpleGUI(Task task) {
//    }


    class ButtonEventListnerCompleted implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            task.setTaskStatus(Status.COMPLETED);
            client.controller.Controller.getInstance().modifyTaskSimpleGUI(task);

//            try {
//                Controller controller = Controller.getController();
//                controller.updateTask(task);
//            } catch (PropertiesFileNotFoundException ex) {
//                ex.printStackTrace();
//            } catch (TaskNotFoundException ex) {
//                ex.printStackTrace();
//            } catch (TaskSchedulerException ex) {
//                ex.printStackTrace();
//            }
            frame.setVisible(false);
        }
    }

    class ButtonEventListnerCanceled implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            task.setTaskStatus(Status.CANCELED);
            client.controller.Controller.getInstance().modifyTaskSimpleGUI(task);
//            try {
//                Controller controller = Controller.getController();
//                controller.updateTask(task);
//            } catch (PropertiesFileNotFoundException ex) {
//                ex.printStackTrace();
//            } catch (TaskNotFoundException ex) {
//                ex.printStackTrace();
//            } catch (TaskSchedulerException ex) {
//                ex.printStackTrace();
//            }
            frame.setVisible(false);
        }
    }

    class ButtonEventListnerOverdue implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SimpleGUI2 simpleGUI2 = new SimpleGUI2(task);
            frame.setVisible(false);
        }
    }

}
