package com.javalab.view;

import com.javalab.controller.Controller;
import com.javalab.exceptions.PropertiesFileNotFoundException;
import com.javalab.exceptions.TaskNotFoundException;
import com.javalab.exceptions.TaskSchedulerException;
import com.javalab.model.Status;
import com.javalab.model.Task;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;


public class SimpleGUI2 extends JFrame {
    private Task task;
    private JFrame frame;
    private JLabel label = new JLabel("На какое время перенести?");
    private JButton buttonOnThe10Minutes = new JButton("На 10 минут");
    private JButton buttonOnThe1hour = new JButton("на 1 час");
    private JButton buttonOnThe1Day = new JButton("На 1 день");
    private JButton buttonOnAnotherVariant = new JButton("Другой вариант");
    private JButton buttonBack = new JButton("Вернуться назад");
    private JTextField textFieldHours = new JTextField();
    private JTextField textFieldMinutes = new JTextField();
    private UtilDateModel model = new UtilDateModel();
    private JDatePanelImpl datePanel = new JDatePanelImpl(model);
    private JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
    private Calendar calendar = Calendar.getInstance();

    public SimpleGUI2(Task task) throws HeadlessException {
        this.task = task;
        frame = new JFrame("Task Manager");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        frame.add(label, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(buttonOnThe10Minutes, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(buttonOnThe1hour, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(buttonOnThe1Day, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(buttonOnAnotherVariant, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(buttonBack, new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        buttonOnThe10Minutes.addActionListener(new ButtonEventListnerOnThe10Minutes());
        buttonOnThe1hour.addActionListener(new ButtonEventListnerCOnThe1hour());
        buttonOnThe1Day.addActionListener(new ButtonEventListnerOnThe1Day());
        buttonOnAnotherVariant.addActionListener(new ButtonEventListnerOnAnotherVariant());
        buttonBack.addActionListener(new ButtonEventListnerBack());

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
    }

    class ButtonEventListnerOnThe10Minutes implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            task.setTaskStatus(Status.PENDING);
            Date dateNow = new Date(System.currentTimeMillis());
            calendar.setTime(dateNow);
            calendar.add(Calendar.MINUTE, 10);
            Date newDate = calendar.getTime();
            task.setDate(newDate);
            try {
                Controller controller = Controller.getController();
                controller.updateTask(task);
            } catch (PropertiesFileNotFoundException ex) {
                ex.printStackTrace();
            } catch (TaskNotFoundException ex) {
                ex.printStackTrace();
            } catch (TaskSchedulerException ex) {
                ex.printStackTrace();
            }
            frame.setVisible(false);
        }
    }

    class ButtonEventListnerCOnThe1hour implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            task.setTaskStatus(Status.PENDING);
            Date dateNow = new Date(System.currentTimeMillis());
            calendar.setTime(dateNow);
            calendar.add(Calendar.HOUR, 1);
            Date newDate = calendar.getTime();
            task.setDate(newDate);
            try {
                Controller controller = Controller.getController();
                controller.updateTask(task);
            } catch (PropertiesFileNotFoundException ex) {
                ex.printStackTrace();
            } catch (TaskNotFoundException ex) {
                ex.printStackTrace();
            } catch (TaskSchedulerException ex) {
                ex.printStackTrace();
            }
            frame.setVisible(false);
        }
    }

    class ButtonEventListnerOnThe1Day implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            task.setTaskStatus(Status.PENDING);
            Date dateNow = new Date(System.currentTimeMillis());
            calendar.setTime(dateNow);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Date newDate = calendar.getTime();
            task.setDate(newDate);
            try {
                Controller controller = Controller.getController();
                controller.updateTask(task);
            } catch (PropertiesFileNotFoundException ex) {
                ex.printStackTrace();
            } catch (TaskNotFoundException ex) {
                ex.printStackTrace();
            } catch (TaskSchedulerException ex) {
                ex.printStackTrace();
            }
            frame.setVisible(false);
        }
    }

    class ButtonEventListnerOnAnotherVariant implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SimpleGUI3 simpleGUI3 = new SimpleGUI3(task);
            frame.setVisible(false);
        }
    }

    class ButtonEventListnerBack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SimpleGUI simpleGUI = new SimpleGUI(task);
            frame.setVisible(false);
        }
    }

}
