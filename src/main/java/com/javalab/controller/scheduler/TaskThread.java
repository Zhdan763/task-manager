package com.javalab.controller.scheduler;

import com.javalab.model.Task;
import com.javalab.view.SimpleGUI;

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
        SimpleGUI app = new SimpleGUI(task);
    }
}
