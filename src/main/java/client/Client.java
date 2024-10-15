package client;

import client.controller.Controller;
import client.view.ConsoleView;

import java.io.IOException;

public class Client {

    public static void main(String[] args) {

        Controller controller = Controller.getInstance();
        ConsoleView consoleView = ConsoleView.getView(controller);
        ClientFacade clientFacade = ClientFacade.getInstance();
        try {
            clientFacade.connect();
        } catch (IOException e) {
            System.out.println("fatal error: " + e.getMessage());
            System.exit(1);

        }
        consoleView.run();
    }
}




