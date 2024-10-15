package server.handlers;

import server.ServerFacade;
import server.controller.Controller;
import server.exceptions.CreateTaskException;
import server.exceptions.HandlerException;
import server.exceptions.TaskSchedulerException;
import shared.command.Command;
import shared.command.TaskConverter;
import shared.constants.Constants;
import shared.model.Task;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;

public class CreateHandler implements Handler {
    @Override
    public void handle(Command command) throws HandlerException {
        Task task = null;
        try {
            task = TaskConverter.getInstance().convert((LinkedHashMap<String, Object>) command.getData(), true);
        } catch (ParseException e) {
            throw new HandlerException(e.getMessage());
        }
        try {
            Controller.getInstance().addTask(task);
        } catch (TaskSchedulerException | CreateTaskException e) {
            Command error = new Command(Constants.ERROR, e.getMessage());
            try {
                ServerFacade.getInstance().send(error);
            } catch (IOException ex) {
                throw new HandlerException(ex.getMessage());
            }
            throw new HandlerException(e.getMessage());
        }
    }
}
