package model;

import java.util.*;

public class Journal {
    private Map<Integer, Task> tasks;


    public Journal(Map<Integer, Task> tasks) {
        this.tasks = tasks;
    }

    public Journal() {
        tasks = new HashMap<>();
    }

    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void removeTask(int id) {
        tasks.remove(id);
    }

    public Task getTask(int id) {

        return tasks.get(id);
    }

    public List<Task> getAllTasks() {
        /*
        List<String> list = new ArrayList<String>();
        tasks.forEach((k, v) -> list.add(v.getTaskName()));
        System.out.println(list);
        for (String nnn : list) {
            System.out.println(nnn);
        }
        */
        List <Task> taskList = new ArrayList<>();
        for (Task task :tasks.values()) {
            taskList.add(task);
        }
        return Collections.unmodifiableList(taskList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journal journal = (Journal) o;
        return tasks.equals(journal.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tasks);
    }

    @Override
    public String toString() {
        return "Journal{" +
                "tasks=" + tasks +
                '}';
    }
}

