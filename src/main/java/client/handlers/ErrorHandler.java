package client.handlers;

import shared.command.Command;

public class ErrorHandler implements Handler{
    @Override
    public void handle(Command command) {
        System.out.println(command.getData());
    }
}
