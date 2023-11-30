package com.javalab.controller.scheduler;

import com.javalab.model.Task;

public class TaskScheduler {

    public void scheduleTask(Task task) {
        TaskThread taskThread=new TaskThread(task);
        taskThread.startTimer();
    }
}

