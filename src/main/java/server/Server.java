package server;

import shared.exceptions.PropertiesFileNotFoundException;

import java.io.IOException;

public class Server {
    public static void main(String[] args) {
        ServerInitializer serverInitializer = ServerInitializer.getInstance();
        try {
            serverInitializer.startServerInitializer();
        } catch (PropertiesFileNotFoundException e) {
            System.out.println("fatal error: " + e.getMessage());
            System.exit(1);
        }

//        ServerStop serverStop = ServerStop.getInstance();
//        serverStop.start();

        ServerFacade serverFacade = ServerFacade.getInstance();
        try {
            serverFacade.connect();
        } catch (IOException e) {
            System.out.println("fatal error: " + e.getMessage());
            System.exit(1);
        }

    }
}






