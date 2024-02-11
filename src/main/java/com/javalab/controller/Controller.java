package com.javalab.controller;


import com.javalab.controller.scheduler.TaskScheduler;
import com.javalab.exceptions.*;
import com.javalab.export.ExportManager;
import com.javalab.importmanager.ImportManager;
import com.javalab.model.Journal;
import com.javalab.model.Status;
import com.javalab.model.Task;
import com.javalab.util.CompareDates;
import com.javalab.util.PropertiesReader;

import java.util.Date;
import java.util.List;

public class Controller {
    private static Controller controller;
    private Journal journal;
    private ExportManager exportManager;
    private ImportManager importManager;
    private PropertiesReader propertiesReader;
    private TaskScheduler taskScheduler;

    private Controller() throws PropertiesFileNotFoundException {
        this.journal = new Journal();
        this.propertiesReader = new PropertiesReader();
        this.exportManager = new ExportManager();
        this.importManager = new ImportManager();
        this.taskScheduler = new TaskScheduler();
    }

    public static synchronized Controller getController() throws PropertiesFileNotFoundException {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public void createTask(String taskName, String description, Date date) throws CreateTaskException, TaskSchedulerException {
        CompareDates.checkDate(date);
        Task task = TaskFactory.createTask(taskName, description, date);
        journal.addTask(task);
        taskScheduler.scheduleTask(task);
    }

    public void createTask(String taskName, String description, Date date, int id) throws CreateTaskException, TaskSchedulerException {
        CompareDates.checkDate(date);
        Task task = TaskFactory.createTask(taskName, description, date, id);
        journal.addTask(task);
        taskScheduler.scheduleTask(task);
    }

    public void deleteTask(int id) throws TaskNotFoundException, TaskSchedulerException {
        if (journal.getTask(id) != null) {
            taskScheduler.cancelTask(journal.getTask(id));
            journal.removeTask(id);
        } else {
            throw new TaskNotFoundException(String.format("Task with id \"%d\" not found", id));
        }
    }

    public void updateTask(Task task) throws TaskNotFoundException, TaskSchedulerException {
        if (journal.getTask(task.getId()) != null) {
            taskScheduler.cancelTask(task);
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

    public Task getTask(int id) throws TaskNotFoundException {
        if (journal.getTask(id) != null) {
            return journal.getTask(id);
        } else {
            throw new TaskNotFoundException(String.format("Task with id \"%d\" not found", id));
        }
    }

    public void checkTask(int id) throws TaskNotFoundException {
        if (journal.getTask(id) != null) {
        } else {
            throw new TaskNotFoundException(String.format("Task with id \"%d\" not found", id));
        }
    }

    public String exportJournal() throws ExportException {
        String dataFormat = propertiesReader.getPropertyByName("data.format");
        String pathToFile = propertiesReader.getPropertyByName("data.file.path");
        exportManager.exportJournal(journal, pathToFile, dataFormat);
        return pathToFile;
    }

    public String importJournal() throws ImportException, TaskSchedulerException {
        String dataFormat = propertiesReader.getPropertyByName("data.format");
        String pathToFile = propertiesReader.getPropertyByName("data.file.path");
        this.journal = importManager.importJournal(pathToFile, dataFormat);
        List<Task> taskList = journal.getAllTasks();
        for (Task task : taskList
        ) {
            if (task.getTaskStatus() == Status.CANCELED) {
            } else if (task.getTaskStatus() == Status.COMPLETED) {
            } else {
                Date dateTask = task.getDate();
                Date dateNow = new Date(System.currentTimeMillis());
                int compare = dateTask.compareTo(dateNow);
                if (compare == 0 || compare == -1) {
                    task.setTaskStatus(Status.OVERDUE);
                } else if (compare == 1) {
                    task.setTaskStatus(Status.PENDING);
                    TaskScheduler taskScheduler = new TaskScheduler();
                    taskScheduler.scheduleTask(task);
                }
            }
        }
        return pathToFile;
    }
}



