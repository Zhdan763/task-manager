import controller.Controller;
import controller.IdGenerator;
import controller.TaskFactory;
import exceptions.TaskNotFoundException;
import model.Journal;
import model.Task;
import view.View;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        IdGenerator idGenerator = IdGenerator.getIdGenerator();
        // Controller controller = new Controller();
        Controller controller = Controller.getController();

        //  Task task1 = TaskFactory.createTask("aaa", "ss", new Date(123), idGenerator.getNextId());
        //Task task2 = taskFactory.createTask("bbb", "saas", new Date(123322), idGenerator.getNextId());
        // Task task2 = TaskFactory.createTask("bbb", "saas", new Date(123322), idGenerator.getNextId());
        controller.createTask("aa", "ssss", new Date(1222));
        controller.createTask("aaa", "ssss", new Date(1222));

        try {
            controller.getTask(1);
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
        }
        try {
            controller.getTask(55);
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        controller.getAllTasks();



       /*
        Task model = new Task();
        model.setTaskName("test");
        model.setDescription("Call to Nikita");
        model.setDate(new Date(1234)); //почекать от 1 янв 1970 млс
        model.setId(idGenerator.getNextId());
        model.setId(0);

        Task model2 = new Task();
        model2.setTaskName("privat");
        model2.setDescription("Call to Andrey");
        model2.setDate(new Date(22222));
        model2.setId(idGenerator.getNextId());

        Task model3 = new Task();
        model3.setTaskName("hp");
        model3.setDescription("Call to Vi");
        model3.setDate(new Date(465481));
        model3.setId(idGenerator.getNextId());

        Journal journal = new Journal();
        journal.addTask(model);
        journal.addTask(model2);
        journal.addTask(model3);
        //journal.removeTask(1);
        System.out.println(journal);
        System.out.println(journal.getTask(2));
        System.out.println(journal.getAllTasks());
*/

    }

}
