package server.handlers;

import server.ServerFacade;
import server.controller.Controller;
import server.exceptions.HandlerException;
import shared.command.Command;
import shared.constants.Constants;

import java.io.IOException;

public class GetAllHandler implements Handler {

    @Override
    public void handle(Command command) throws HandlerException {
        Controller controller = Controller.getInstance();
        Command command2 = new Command(Constants.GET_ALL, controller.getAllTasks(), command.getClientId());
        try {
            ServerFacade.getInstance().send(command2);

        } catch (IOException e) {
            throw new HandlerException(e.getMessage());
        }
    }
}
