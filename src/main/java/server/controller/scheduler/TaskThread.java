package server.controller.scheduler;

import server.ServerFacade;
import shared.command.Command;
import shared.constants.Constants;
import shared.model.Task;

import java.io.IOException;
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

        Command command = new Command(Constants.NOTIFICATION, task);
        try {
            ServerFacade.getInstance().sendAll(command);
        } catch (IOException e) {
            e.printStackTrace();
        }


//        SimpleGUI app = new SimpleGUI(task);
    }
}
