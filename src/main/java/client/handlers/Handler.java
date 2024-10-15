package client.handlers;

import shared.command.Command;

public interface Handler {
    public void handle (Command command);
}
