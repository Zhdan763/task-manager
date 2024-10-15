package server.controller;


import server.controller.scheduler.TaskScheduler;
import server.exceptions.CreateTaskException;
import server.exceptions.TaskNotFoundException;
import server.exceptions.TaskSchedulerException;
import server.util.CompareDates;
import shared.model.Journal;
import shared.model.Status;
import shared.model.Task;

import java.util.List;

public class Controller {
    private static Controller controller;
    private Journal journal;
    private TaskScheduler taskScheduler;

    private Controller() {
        this.journal = new Journal();
        this.taskScheduler = new TaskScheduler();
    }

    public static synchronized Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public void addTask(Task task) throws TaskSchedulerException, CreateTaskException {
        CompareDates.setStatus(task);
        journal.addTask(task);
        taskScheduler.scheduleTask(task);
    }

    public void deleteTask(int id) throws TaskNotFoundException, TaskSchedulerException {
        if (journal.getTask(id) != null) {
            taskScheduler.cancelTask(journal.getTask(id));
            journal.removeTask(id);
        } else throw new TaskNotFoundException(String.format("Task with id \"%d\" not found", id));
    }

    public void updateTask(Task task) throws TaskNotFoundException, CreateTaskException, TaskSchedulerException {
        if (journal.getTask(task.getId()) != null) {
            try {
                taskScheduler.cancelTask(task);
            } catch (TaskSchedulerException ignored) {

            }
            journal.removeTask(task.getId());
            if (task.getTaskStatus() == Status.CANCELED || task.getTaskStatus() == Status.COMPLETED) {
                journal.addTask(task);
            } else {
                journal.addTask(task);
                taskScheduler.scheduleTask(task);
            }
        } else {
            throw new TaskNotFoundException(String.format("Task with id \"%d\" not found", task.getId()));
        }
    }

    public List<Task> getAllTasks() {
        return journal.getAllTasks();
    }
}

//”¡–¿À œŒ—À≈ œ≈–≈ƒ≈À€¬¿Õ»ﬂ —≈–¬≈–»Õ»ÿ¿À¿…«≈–
//    public void export() {
//        ServerInitialiser serverInitialiser=ServerInitialiser.getInstance();
//        serverInitialiser.getProperties("data.format");
//        String dataFormat = serverInitialiser.getProperties("data.format");
//        String pathToFile = serverInitialiser.getProperties("data.file.path");
//        try {
//            exportManager.exportJournal(journal,pathToFile, dataFormat);
//        } catch (ExportException e) {
//            e.printStackTrace();
//        }
//    }
//
//
