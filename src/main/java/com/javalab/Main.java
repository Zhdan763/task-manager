package com.javalab;

import com.javalab.controller.Controller;
import com.javalab.exceptions.ExportException;
import com.javalab.exceptions.PropertiesFileNotFoundException;
import com.javalab.view.View;

import java.util.Date;

public class Main {

    public static void main(String[] args) {


        Controller controller = null;
        try {
            controller = Controller.getController();

        } catch (PropertiesFileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        View view = View.getView(controller);
       // controller.createTask("aaa", "bb", new Date(45441));
       // controller.createTask("bbbbb", "bb", new Date(45441));
        // controller.exportJson(0);
/*
        try {
            controller.exportTask(0);
        } catch (ExportException e) {
            System.out.println(e.getMessage());
        }

 */

        view.run();
    }

}
