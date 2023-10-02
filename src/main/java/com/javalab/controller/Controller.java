package com.javalab.controller;


import com.javalab.exceptions.ExportException;
import com.javalab.exceptions.ImportException;
import com.javalab.exceptions.PropertiesFileNotFoundException;
import com.javalab.exceptions.TaskNotFoundException;
import com.javalab.export.ExportManager;
import com.javalab.importmanager.ImportManager;
import com.javalab.model.Journal;
import com.javalab.model.Task;
import com.javalab.util.PropertiesReader;
import java.util.Date;
import java.util.List;

public class Controller {
    private static Controller controller;
    private Journal journal;
    private ExportManager exportManager;
    private ImportManager importManager;
    private PropertiesReader propertiesReader;

    private Controller() throws PropertiesFileNotFoundException {
        this.journal = new Journal();
        this.propertiesReader = new PropertiesReader();
        this.exportManager = new ExportManager();
        this.importManager = new ImportManager();
    }

    public static synchronized Controller getController() throws PropertiesFileNotFoundException {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public void createTask(String taskName, String description, Date date) {
        Task task = TaskFactory.createTask(taskName, description, date);
        journal.addTask(task);
    }

    public void createTask(String taskName, String description, Date date, int id) {
        Task task = TaskFactory.createTask(taskName, description, date, id);
        journal.addTask(task);
    }

    public void deleteTask(int id) throws TaskNotFoundException {
        if (journal.getTask(id) != null) {
            journal.removeTask(id);
        } else {
            throw new TaskNotFoundException(String.format("Task with id \"%d\" not found", id));
        }
    }

    public void updateTask(Task task) throws TaskNotFoundException {
        if (journal.getTask(task.getId()) != null) {
            journal.removeTask(task.getId());
            journal.addTask(task);
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

    public void exportTask(int id) throws ExportException {
        String filePath = propertiesReader.getPropertyByName("data.file.path");
        Task task = journal.getTask(id);
        exportManager.exportTask(task, filePath);
    }

    public String exportJournal() throws ExportException {
        String dataFormat = propertiesReader.getPropertyByName("data.format");
        String pathToFile = propertiesReader.getPropertyByName("data.file.path");
        exportManager.exportJournal(journal, pathToFile, dataFormat);
        return pathToFile;
    }

    public String importJournal() throws ImportException {
        String dataFormat = propertiesReader.getPropertyByName("data.format");
        String pathToFile = propertiesReader.getPropertyByName("data.file.path");
        this.journal = importManager.importJournal(pathToFile, dataFormat);
        return pathToFile;
    }
}



