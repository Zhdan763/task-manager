package client.view;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import shared.model.Status;
import shared.model.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class SimpleGUI3 extends JFrame {
    private Task task;
    private JLabel labelDate = new JLabel("Дата");
    private JLabel labelHours = new JLabel("Часы");
    private JLabel labelMinutes = new JLabel("Минуты");
    private JTextField textFieldHours = new JTextField();
    private JTextField textFieldMinutes = new JTextField();
    private JButton buttonOk = new JButton("Ok");
    private UtilDateModel model = new UtilDateModel();
    private JDatePanelImpl datePanel = new JDatePanelImpl(model);
    private JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
    private Calendar calendar = Calendar.getInstance();
    private LocalDateTime localDateTime = LocalDateTime.now();
    private SpinnerModel hours = new SpinnerNumberModel(localDateTime.getHour(), 0, 23, 1);
    private SpinnerModel minutes = new SpinnerNumberModel(localDateTime.getMinute(), 0, 59, 1);
    private JSpinner spinnerHours = new JSpinner(hours);
    private JSpinner spinnerMinutes = new JSpinner(minutes);
    private JFrame frame;

    public SimpleGUI3(Task task) throws HeadlessException {
        this.task = task;

        frame = new JFrame("Task Manager");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        frame.add(labelDate, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(labelHours, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(labelMinutes, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(datePicker, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(spinnerHours, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(spinnerMinutes, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        frame.add(buttonOk, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        buttonOk.addActionListener(new ButtonEventListnerOk());

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
    }

    class ButtonEventListnerOk implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            task.setTaskStatus(Status.PENDING);
            Date date = (Date) datePicker.getModel().getValue();
            int hours = (Integer) spinnerHours.getValue();
            int minutes = (Integer) spinnerMinutes.getValue();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, hours);
            calendar.set(Calendar.MINUTE, minutes);
            Date newDate = calendar.getTime();
            Date dateNew = new Date(System.currentTimeMillis());
            int compare = newDate.compareTo(dateNew);
            if (compare == 0 || compare == -1) {
                JOptionPane.showMessageDialog(null, "Date cannot be less than current", "Error", JOptionPane.PLAIN_MESSAGE);
            } else if (compare == 1) {
                task.setDate(newDate);
                client.controller.Controller.getInstance().modifyTaskSimpleGUI(task);
//                try {
//                    Controller controller = Controller.getController();
//                    controller.updateTask(task);
//                } catch (PropertiesFileNotFoundException ex) {
//                    ex.printStackTrace();
//                } catch (TaskNotFoundException ex) {
//                    ex.printStackTrace();
//                } catch (TaskSchedulerException ex) {
//                    ex.printStackTrace();
//                }
            }
            frame.setVisible(false);
        }
    }
}
