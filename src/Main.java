import controller.Controller;
import model.Task;
import view.View;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       // Task model = aaa();
        Task model = new Task();
        Scanner a= new Scanner(System.in);
        //model.setTaskName("Call");
        String b=a.nextLine();
        model.setTaskName(b);
        model.setDescription("Call to Nikita");
        model.setContacts(1234);

        View view = new View();
        Controller controller = new Controller(model, view);
        controller.display();

    }
   /* private static Task aaa () {
        Task task = new Task();
        task.setTaskName("iihaiBALA");
        return task;
    }

    */
}
