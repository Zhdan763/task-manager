package client.controller;

import client.ClientFacade;
import client.command.CommandListener;
import shared.command.Command;
import shared.constants.Constants;
import shared.model.Status;
import shared.model.Task;
import shared.util.TaskFactory;

import java.util.Date;

public class Controller {
    private static Controller controller;

    public static synchronized Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public void showTasks() {
        Command command = new Command(Constants.GET_ALL, null);
        ClientFacade.getInstance().sendCommand(command);
    }

    public void createTask(String taskName, String description, Date date) {
        Task task = TaskFactory.createTask(taskName, description, date);
        Command command = new Command(Constants.CREATE, task);
        ClientFacade.getInstance().sendCommand(command);
    }

    public void deleteTask(Integer id) {
        Command command = new Command(Constants.DELETE, id);
        ClientFacade.getInstance().sendCommand(command);
    }

    public void modifyTask(String taskName, String description, Date date, Integer id, Status status) {
        Task task = TaskFactory.createTask(taskName, description, date, id, status);
        Command command = new Command(Constants.MODIFY, task);
        ClientFacade.getInstance().sendCommand(command);
    }

    public void exit() {
        Command command = new Command(Constants.DISCONNECT, null);
        ClientFacade.getInstance().sendCommand(command);
        ClientFacade clientFacade = ClientFacade.getInstance();
        CommandListener commandListener = clientFacade.getCommandListener();
        commandListener.interrupt();
        clientFacade.closeStreams();
        clientFacade.deleteComList();
    }

    public void modifyTaskSimpleGUI(Task task) {
        Command command = new Command(Constants.MODIFY, task);
        ClientFacade.getInstance().sendCommand(command);
    }

}
