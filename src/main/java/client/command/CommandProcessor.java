package client.command;

import client.handlers.*;
import shared.command.Command;
import shared.constants.Constants;

import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {
    private static CommandProcessor commandProcessor;
    private Map<Integer, Handler> handlerMap;

    public static synchronized CommandProcessor getInstance() {
         if (commandProcessor == null) {
            commandProcessor = new CommandProcessor();
        }
        return commandProcessor;
    }

    private CommandProcessor () {
        handlerMap = new HashMap<>();
        handlerMap.put(Constants.GET_ALL, new GetAllHandler());
        handlerMap.put(Constants.DISCONNECT, new DisconnectHandler());
        handlerMap.put(Constants.NOTIFICATION, new NotificationHandler());
        handlerMap.put(Constants.ERROR, new ErrorHandler());
    }

    public void processCommand(Command command) {
        int commandId=command.getId();
        handlerMap.get(commandId).handle(command);
    }

    }


