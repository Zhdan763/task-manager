package controller;


import model.Task;
import view.View;

public class Controller {
    private Task model;
    private View view;

    public Controller(Task model, View view) {
        this.model = model;
        this.view = view;
    }

    public void display() {
        view.printInfo(model.getTaskName(), model.getDescription(), model.getContacts());
    }

}



