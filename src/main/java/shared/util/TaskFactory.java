package shared.util;

import shared.model.Status;
import shared.model.Task;

import java.util.Date;

public class TaskFactory {
    public static Task createTask(String taskName, String description, Date date, int id) {
        Task task = new Task();
        task.setTaskName(taskName);
        task.setDescription(description);
        task.setDate(date);
        task.setId(id);
        task.setTaskStatus(Status.PENDING);
        return task;
    }

    public static Task createTask(String taskName, String description, Date date) {
        Task task = new Task();
        task.setTaskName(taskName);
        task.setDescription(description);
        task.setDate(date);
        task.setTaskStatus(Status.PENDING);
        return task;
    }

    public static Task createTask(String taskName, String description, Date date, int id, Status status) {
        Task task = new Task();
        task.setTaskName(taskName);
        task.setDescription(description);
        task.setDate(date);
        task.setId(id);
        task.setTaskStatus(status);
        return task;


    }
}
