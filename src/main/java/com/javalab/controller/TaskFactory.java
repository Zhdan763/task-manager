package com.javalab.controller;

import com.javalab.model.Status;
import com.javalab.model.Task;

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
        task.setId(IdGenerator.getIdGenerator().getNextId());
        task.setTaskStatus(Status.PENDING);
        return task;
    }


}
