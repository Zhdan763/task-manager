package view;

import controller.Controller;
import controller.IdGenerator;
import exceptions.TaskNotFoundException;
import model.Task;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class View {
    private static View view;
    private Scanner scanner;


    Controller controller = Controller.getController();

    public static View getView() {
        if (view == null) {
            view = new View();
        }
        return view;
    }

    private View() {
        this.scanner = new Scanner(System.in);

    }


    public void run() throws ParseException, TaskNotFoundException {
        int menuAction = -1;

        System.out.println("Task Manager");
        while (menuAction != 5) {
            menuAction = runMenu();
            switch (menuAction) {
                case 1:
                    showTasks();
                    break;
                case 2:
                    createTask();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    modifyTask();
                    break;
                case 5:
                    System.out.println("\n Good bye!");
                    break;
                default:
                    System.out.println("Error: Incorrect value, please choose correct");
                    break;
            }
        }
    }

    private int runMenu() {
        System.out.println();
        System.out.println("Choose action:");
        System.out.println("1. Display all tasks");
        System.out.println("2. Create tasks");
        System.out.println("3. Delete task");
        System.out.println("4. Modify task");
        System.out.println("5. Exit");

        System.out.print("\nAction: ");
        String input = scanner.nextLine();
        return Integer.valueOf(input);
    }

    private void showTasks() {
        System.out.println("Tasks: ");
        for (Task i : controller.getAllTasks()) {
            System.out.println(i);
        }

        controller.getAllTasks();
        System.out.print("Press \"Enter\" for return to the main menu...\n");
        scanner.nextLine();
    }

    private void createTask() throws ParseException {
        System.out.println("\nNew Task:");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        Date date;
        while (true) {
            System.out.print("Enter date (yyyy-mm-dd): ");
            String dateAa = scanner.nextLine();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
            try {
                date = formatter.parse(dateAa);
                System.out.println("Correct date");
                break;
            } catch (ParseException e) {
                System.out.println("Input is not a date, continue");
            }
        }
        controller.createTask(name, description, date);
        System.out.println("\nInfo: Task has been added!");

    }


    private void deleteTask() throws TaskNotFoundException {
        System.out.println("What task do you want to delete?");
        System.out.print("\nEnter id: ");
        // String id = scanner.nextLine();
        // Integer id2 = Integer.valueOf(id);
        //Integer idForDel = id2;
        //controller.deleteTask(idForDel);
        // System.out.println("Task number "+idForDel+" has been deleted");
        String id = scanner.nextLine();
        int id2 = Integer.valueOf(id);
        controller.deleteTask(id2);
        System.out.println("Task number " + id2 + " has been deleted");
    }

    private void modifyTask() throws TaskNotFoundException {
        System.out.println("What task do you want to change?");
        System.out.print("\nEnter id: ");
        String id = scanner.nextLine();
        int id2 = Integer.valueOf(id);

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        Date date;
        while (true) {
            System.out.print("Enter date (yyyy-mm-dd): ");
            String dateAa = scanner.nextLine();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
            try {
                date = formatter.parse(dateAa);
                System.out.println("Correct date");
                break;
            } catch (ParseException e) {
                System.out.println("Input is not a date, continue");
            }
        }

        controller.createTask(name, description, date, id2);
        controller.updateTask(controller.getTask(id2));

    }


}

