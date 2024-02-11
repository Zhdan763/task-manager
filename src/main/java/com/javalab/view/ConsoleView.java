package com.javalab.view;

import com.javalab.controller.Controller;
import com.javalab.exceptions.*;
import com.javalab.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleView {
    private static ConsoleView consoleView;
    private Controller controller;
    private final Scanner scanner;

    private ConsoleView(Controller controller) {
        this.scanner = new Scanner(System.in);
        this.controller = controller;
    }

    public static ConsoleView getView(Controller controller) {
        if (consoleView == null) {
            consoleView = new ConsoleView(controller);

        }
        return consoleView;
    }

    public void run() {
        int menuAction = -1;
        System.out.println("Task Manager");
        while (menuAction != 7) {
            menuAction = runMenu();
            switch (menuAction) {
                case 1:
                    showTasks();
                    break;
                case 2:
                    try {
                        createTask();
                    } catch (CreateTaskException | TaskSchedulerException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    try {
                        modifyTask();
                    } catch (CreateTaskException | TaskSchedulerException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        exportJournal();
                    } catch (ExportException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        importJournal();
                    } catch (ImportException e) {
                        System.out.println(e.getMessage());
                    } catch (TaskSchedulerException e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
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
        System.out.println("5. Export journal");
        System.out.println("6. Import journal");
        System.out.println("7. Exit");
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

    private void createTask() throws CreateTaskException, TaskSchedulerException {
        System.out.println("\nNew Task:");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        Date date;
        while (true) {
            System.out.print("Enter date (yyyy-MM-dd HH:mm): ");
            String dateAa = scanner.nextLine();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
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

    private void deleteTask() {
        System.out.println("What task do you want to delete?");
        System.out.print("\nEnter id: ");
        String id = scanner.nextLine();
        int id2 = Integer.valueOf(id);
        try {
            controller.deleteTask(id2);
            System.out.println("Task number " + id2 + " has been deleted");
        } catch (TaskNotFoundException | TaskSchedulerException e) {
            System.out.println(e.getMessage());
        }
    }

    private void modifyTask() throws CreateTaskException, TaskSchedulerException {
        System.out.println("What task do you want to change?");
        System.out.print("\nEnter id: ");
        String id = scanner.nextLine();
        int id2 = Integer.valueOf(id);
        try {
            controller.checkTask(id2);
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        Date date;
        while (true) {
            System.out.print("Enter date (yyyy-MM-dd HH:mm): ");
            String dateAa = scanner.nextLine();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
            try {
                date = formatter.parse(dateAa);
                System.out.println("Correct date");
                break;
            } catch (ParseException e) {
                System.out.println("Input is not a date, continue");
            }
        }
        controller.createTask(name, description, date, id2);
        try {
            controller.updateTask(controller.getTask(id2));
        } catch (TaskNotFoundException | TaskSchedulerException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void exportJournal() throws ExportException {
        String filePath = controller.exportJournal();
        System.out.println("Journal save to the path: " + filePath);
    }

    private void importJournal() throws ImportException, TaskSchedulerException {
        String filePath = controller.importJournal();
        System.out.println("Journal was imported from a file: " + filePath);
    }

}

