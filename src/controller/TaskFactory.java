package controller;

import model.Task;
import model.Journal;

import java.util.Date;

public class TaskFactory {
    public static Task createTask(String taskName, String description, Date date, int id) {
        Task task = new Task();
        task.setTaskName(taskName);
        task.setDescription(description);
        task.setDate(date);
        task.setId(id);
        return task;
    }

    public static Task createTask(String taskName, String description, Date date) {
        Task task = new Task();
        task.setTaskName(taskName);
        task.setDescription(description);
        task.setDate(date);
        task.setId(IdGenerator.getIdGenerator().getNextId());
        return task;
    }

}
