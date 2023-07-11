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
    //  public static void main(String[] args) throws ParseException, TaskNotFoundException {
    public static void main(String[] args) {
        // propertyreader = new propertyreader
        // 2 принта формат данных и путь до данных
        IdGenerator idGenerator = IdGenerator.getIdGenerator();
        Controller controller = Controller.getController();
        View view = View.getView();
        view.run();


    }

}
