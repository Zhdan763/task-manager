package server;

import server.controller.Controller;
import server.exceptions.CreateTaskException;
import server.exceptions.TaskSchedulerException;
import shared.exceptions.PropertiesFileNotFoundException;
import shared.model.Journal;
import shared.model.Task;
import shared.util.Constants;
import shared.util.IdGenerator;
import shared.util.JsonSerializer;
import shared.util.PropertiesReader;

import java.io.IOException;

public class ServerInitializer {
    private static ServerInitializer serverInitializer;
    private int maxId;

    public static synchronized ServerInitializer getInstance() {
        if (serverInitializer == null) {
            serverInitializer = new ServerInitializer();
        }
        return serverInitializer;
    }

    private ServerInitializer() {
        this.maxId = 0;
    }

    public void startServerInitializer() throws PropertiesFileNotFoundException {

        PropertiesReader propertiesReader = new PropertiesReader(Constants.PROPERTY_FILE_PATH);
        String dataFilePath = propertiesReader.getPropertyByName("data.file.path");
        try {
            Journal journal = JsonSerializer.getInstance().readValueFromFile(dataFilePath, Journal.class);
            for (Task task : journal.getTasks().values()) {
                if (task.getId() > maxId) {
                    maxId = task.getId();
                }
                try {
                    Controller.getInstance().addTask(task);
                } catch (TaskSchedulerException e) {
                    System.out.println(e.getMessage());
                } catch (CreateTaskException ignored) {

                }
            }
            IdGenerator.getIdGenerator(maxId).getNextId();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
