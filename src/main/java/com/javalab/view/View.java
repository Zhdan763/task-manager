package com.javalab.view;

import com.javalab.controller.Controller;
import com.javalab.exceptions.ExportException;
import com.javalab.exceptions.ImportException;
import com.javalab.exceptions.TaskNotFoundException;
import com.javalab.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class View {
    private static View view;
    private Controller controller;
    private final Scanner scanner;

    private View(Controller controller) {
        this.scanner = new Scanner(System.in);
        this.controller = controller;
    }

    public static View getView(Controller controller) {
        if (view == null) {
            view = new View(controller);

        }
        return view;
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
                    createTask();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    modifyTask();
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

    private void createTask() {
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

    private void deleteTask() {
        System.out.println("What task do you want to delete?");
        System.out.print("\nEnter id: ");
        String id = scanner.nextLine();
        int id2 = Integer.valueOf(id);
        try {
            controller.deleteTask(id2);
            System.out.println("Task number " + id2 + " has been deleted");
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void modifyTask() {
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
        try {
            controller.updateTask(controller.getTask(id2));
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void exportJournal() throws ExportException {
        String filePath = controller.exportJournal();
        System.out.println("Journal save to the path: " + filePath);
    }

    private void importJournal() throws ImportException {
        String filePath = controller.importJournal();
        System.out.println("Journal was imported from a file: " + filePath);
    }
}

