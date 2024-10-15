package server.command;

import server.exceptions.HandlerException;
import server.handlers.*;
import shared.command.Command;
import shared.constants.Constants;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {
    private static CommandProcessor commandProcessor;

    public static synchronized CommandProcessor getInstance() {
        if (commandProcessor == null) {
            commandProcessor = new CommandProcessor();
        }
        return commandProcessor;
    }

    Map<Integer, Handler> handlerMap;

    public CommandProcessor() {
        handlerMap = new HashMap<>();
        handlerMap.put(Constants.GET_ALL, new GetAllHandler());
        handlerMap.put(Constants.CREATE, new CreateHandler());
        handlerMap.put(Constants.DELETE, new DeleteHandler());
        handlerMap.put(Constants.MODIFY, new ModifyHandler());
        handlerMap.put(Constants.DISCONNECT, new DisconnectHandler());
        handlerMap.put(Constants.ERROR, new ErrorHandler());
        handlerMap.put(Constants.NOTIFICATION, new NotificationHandler());

    }

    public void processCommand(Command command) throws ParseException, IOException, HandlerException {
        int commandId=command.getId();
        handlerMap.get(commandId).handle(command);
    }

}
