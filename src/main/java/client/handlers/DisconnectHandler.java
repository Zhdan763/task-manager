package client.handlers;

import client.ClientFacade;
import client.command.CommandListener;
import shared.command.Command;

public class DisconnectHandler implements Handler {
    @Override
    public void handle(Command command) {
//        System.out.println(command.getData());
        if(command.getData() != null) {
            System.out.println(command.getData());
        }
//        CommandListener commandListener = ClientFacade.getInstance().getCommandListener(0);
//        commandListener.interrupt();
//        ClientFacade.getInstance().deleteComList(0);
    }
}
