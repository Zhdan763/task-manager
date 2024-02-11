package com.javalab.controller.scheduler;

import com.javalab.exceptions.TaskSchedulerException;
import com.javalab.model.Task;

import java.util.HashMap;
import java.util.Map;

public class TaskScheduler {
    private Map<Integer, TaskThread> map;

    public TaskScheduler() {
        this.map = new HashMap<>();
    }

    public void scheduleTask(Task task) throws TaskSchedulerException {
        if (!map.containsKey(task.getId())) {
            TaskThread taskThread = new TaskThread(task);
            map.put(task.getId(), taskThread);
            taskThread.startTimer();
        } else {
            cancelTask(task);
            TaskThread taskThread = new TaskThread(task);
            map.put(task.getId(), taskThread);
            taskThread.startTimer();
        }
    }

    public void cancelTask(Task task) throws TaskSchedulerException {
        if (map.containsKey(task.getId())) {
            map.get(task.getId()).cancelTimer();
        } else {
            throw new TaskSchedulerException("Task not found");
        }


    }

}

