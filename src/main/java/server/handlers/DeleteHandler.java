package server.handlers;

import server.ServerFacade;
import server.controller.Controller;
import server.exceptions.HandlerException;
import server.exceptions.TaskNotFoundException;
import server.exceptions.TaskSchedulerException;
import shared.command.Command;
import shared.constants.Constants;

import java.io.IOException;

public class DeleteHandler implements Handler {
    @Override
    public void handle(Command command) throws HandlerException {
        int id = (Integer) command.getData();
        try {
            Controller controller = Controller.getInstance();
            controller.deleteTask(id);
        } catch (TaskNotFoundException e) {
            Command error = new Command(Constants.ERROR, e.getMessage());
            try {
                ServerFacade.getInstance().send(error);
            } catch (IOException ex) {
                throw new HandlerException(ex.getMessage());
            }
        } catch (TaskSchedulerException e) {
            throw new HandlerException(e.getMessage());
        }

    }
}
