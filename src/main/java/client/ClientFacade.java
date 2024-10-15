package client;

import client.command.CommandListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import shared.command.Command;
import shared.util.JsonSerializer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientFacade {
    private static ClientFacade clientFacade;
    private Map<Integer, CommandListener> commandListenerMap;
    private Map<Integer, DataOutputStream> outputStreamMap;

    public static synchronized ClientFacade getInstance() {
        if (clientFacade == null) {
            clientFacade = new ClientFacade();
        }
        return clientFacade;
    }

    public ClientFacade() {
        this.commandListenerMap = new HashMap<>();
        this.outputStreamMap = new HashMap<>();
    }

    public void connect() throws IOException {
        Socket s = new Socket("localhost", 8000);
        System.out.println("Client connected to socket");
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        outputStreamMap.put(0, out);
        CommandListener commandListener = new CommandListener(s);
        commandListenerMap.put(0, commandListener);
        commandListener.start();
    }

    public CommandListener getCommandListener() {
        return commandListenerMap.get(0);
    }

    public void sendCommand(Command command) {
        try {
            outputStreamMap.get(0).writeUTF(JsonSerializer.getInstance().writeJson(command));
            outputStreamMap.get(0).flush();
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeStreams() {
        try {
            outputStreamMap.get(0).close();
            commandListenerMap.get(0).getInputStream().close();
            commandListenerMap.get(0).getSocket().close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteComList() {
        commandListenerMap.remove(0);
    }

}




