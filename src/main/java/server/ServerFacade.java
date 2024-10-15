package server;

import server.command.CommandListener;
import server.util.ClientIdGenerator;
import shared.command.Command;
import shared.util.JsonSerializer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerFacade {
    private static ServerFacade serverFacade;
    private Map<Integer, Client> clientMap;

    public static synchronized ServerFacade getInstance() {
        if (serverFacade == null) {
            serverFacade = new ServerFacade();
        }
        return serverFacade;
    }

    public ServerFacade() {
        this.clientMap = new HashMap<>();
    }

    public void connect() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        while (!serverSocket.isClosed()) {
            Socket socket = serverSocket.accept();
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            int clientId = ClientIdGenerator.getInstance().getNextClientId();
            System.out.println("Client with id: " + clientId + " connected");
            CommandListener commandListener = new CommandListener(dataInputStream, clientId);
            commandListener.start();
            Client client = new Client(clientId, socket, commandListener, dataOutputStream);
            clientMap.put(clientId, client);
        }

    }

    public void send(Command command) throws IOException {
        int clientId = command.getClientId();
        clientMap.get(clientId).getOutputStream().writeUTF(JsonSerializer.getInstance().writeJson(command));
        clientMap.get(clientId).getOutputStream().flush();
    }

    public void sendAll(Command command) throws IOException {
        int allClient = clientMap.size();
        for (int i = 0; i < allClient; i++) {
            clientMap.get(i).getOutputStream().writeUTF(JsonSerializer.getInstance().writeJson(command));
            clientMap.get(i).getOutputStream().flush();
        }
    }

    public void closeStreams(int clientId) {
        try {
            clientMap.get(clientId).getOutputStream().close();
            clientMap.get(clientId).getCommandListener().getInputStream().close();
            clientMap.get(clientId).getSocket().close();
            System.out.println("Input and Output Streams client with id: " + clientId + " - close");
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }

    public CommandListener getCommandListener(int comListId) {
        return clientMap.get(comListId).getCommandListener();
    }

    public void deleteClient(int clientId) {
        clientMap.remove(clientId);
        System.out.println("delete clientMap: " + clientMap);
    }

    public void stopServer() {
//        Command command = new Command(Constants.DISCONNECT, "Server shut down, try again later");
//        try {
//            send(command);
//            commandListenerMap.get(1).interrupt();
//            deleteComList(1);
////            closeStreams();
//            out.close();
//            in.close();
//            client.close();
////            serverSocket.close();
////            ServerFacade.getInstance().interrupt();
//            ServerStop.getInstance().interrupt();
//            System.out.println("after interrupt serverstop");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        try {
//            serverSocket.close();
//            ServerStop.getInstance().interrupt();
//            System.out.println("after serverstop");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

}
