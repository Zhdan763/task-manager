package com.javalab;

import com.javalab.controller.Controller;
import com.javalab.exceptions.PropertiesFileNotFoundException;
import com.javalab.view.ConsoleView;

public class Main {

    public static void main(String[] args) {
        Controller controller = null;
        try {
            controller = Controller.getController();

        } catch (PropertiesFileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        ConsoleView consoleView = ConsoleView.getView(controller);
        consoleView.run();
    }

}
