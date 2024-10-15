package server;

import server.controller.Controller;
import server.controller.scheduler.TaskScheduler;

import java.util.Scanner;

public class ServerStop extends Thread {
    private static ServerStop serverStop;

    public static synchronized ServerStop getInstance() {
        if (serverStop == null) {
            serverStop = new ServerStop();
        }
        return serverStop;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            Scanner scanner = new Scanner(System.in);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            String text = scanner.nextLine();
            if (text.equalsIgnoreCase("exit")) {
                System.out.println("stop server");
               ;
//                Controller.getInstance().export();
                ServerFacade.getInstance().stopServer();
            }
        }

    }
}
