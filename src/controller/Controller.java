package controller;


import exceptions.TaskNotFoundException;
import model.Journal;
import model.Task;
import view.View;

import java.util.Date;
import java.util.List;

public class Controller {
    private Journal journal;
    private static Controller controller;

    private Controller(Journal journal) {
        this.journal = journal;
    }

    private Controller() {
        this.journal = new Journal();
    }

    public static synchronized Controller getController() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public void createTask(String taskName, String description, Date date) {
//1. ������� ������ ����� �������. 2. �������� � ������. �� ���� ��������� ����� �������.createTask
        Task task = TaskFactory.createTask(taskName, description, date);
        journal.addTask(task);
    }

    public void createTask(String taskName, String description, Date date, int id) {
        Task task = TaskFactory.createTask(taskName, description, date, id);
        journal.addTask(task);
    }

    public void deleteTask(int id) {  //�������� ��� ��������, ���� ���, �� ������ �� ������� ��� ������
        journal.removeTask(id);
    }

    public void updateTask(Task task) throws TaskNotFoundException {
//�������� �������� ������������� � ������� �������� � ������
        if (journal.getTask(task.getId()) != null) {
            journal.removeTask(task.getId());
            journal.addTask(task);
        } else {
            throw new TaskNotFoundException(String.format("Task with id \"%d\" not found", task.getId()));
        }
    }

    public List<Task> getAllTasks() {
//������� ���� ����� (� �� ���)
        return journal.getAllTasks();
    }

    public Task getTask(int id) throws TaskNotFoundException {  //����� �� ������
        //return null;
        if (journal.getTask(id) != null) {
            return journal.getTask(id);
        } else {
            throw new TaskNotFoundException(String.format("Task with id \"%d\" not found", id));
        }
        // return null;
    }

}



