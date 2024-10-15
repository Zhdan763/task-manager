package server.handlers;

import server.exceptions.HandlerException;
import shared.command.Command;

public interface Handler {
    public void handle(Command command) throws HandlerException;
}
