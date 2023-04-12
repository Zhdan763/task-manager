import controller.Controller;
import controller.IdGenerator;
import controller.TaskFactory;
import exceptions.TaskNotFoundException;
import model.Journal;
import model.Task;
import view.View;

import java.text.ParseException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ParseException, TaskNotFoundException {
        IdGenerator idGenerator = IdGenerator.getIdGenerator();
        Controller controller = Controller.getController();
        View view = View.getView();



        controller.createTask("aa", "ssss", new Date(1222));
        controller.createTask("aaa", "ssss", new Date(1222));

        view.run();

        /*
        try {
            controller.getTask(1);
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
        }

        try {
            controller.getTask(0);
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        controller.getAllTasks();
*/

    }

}
