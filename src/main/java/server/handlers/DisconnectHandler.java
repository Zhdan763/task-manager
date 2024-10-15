package server.handlers;

import server.ServerFacade;
import server.command.CommandListener;
import shared.command.Command;

public class DisconnectHandler implements Handler {
    @Override
    public void handle(Command command) {
        ServerFacade serverFacade = ServerFacade.getInstance();
        int clientId = command.getClientId();
        CommandListener commandListener = serverFacade.getCommandListener(clientId);
        commandListener.interrupt();
        serverFacade.closeStreams(clientId);
        serverFacade.deleteClient(clientId);

    }
}
