package client.handlers;

import client.view.SimpleGUI;
import shared.command.Command;
import shared.command.TaskConverter;
import shared.model.Task;

import java.text.ParseException;
import java.util.LinkedHashMap;

public class NotificationHandler implements Handler{
    @Override
    public void handle(Command command) {

        try {
            Task task = TaskConverter.getInstance().convert((LinkedHashMap<String, Object>) command.getData(), false);
            SimpleGUI simpleGUI=new SimpleGUI(task);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
