package server;

import server.command.CommandListener;

import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    private int clientId;
    private Socket socket;
    private CommandListener commandListener;
    private DataOutputStream outputStream;

    public Client(int clientId, Socket socket, CommandListener commandListener, DataOutputStream outputStream) {
        this.clientId = clientId;
        this.socket = socket;
        this.commandListener = commandListener;
        this.outputStream = outputStream;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public CommandListener getCommandListener() {
        return commandListener;
    }

    public void setCommandListener(CommandListener commandListener) {
        this.commandListener = commandListener;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
