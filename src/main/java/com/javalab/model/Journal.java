package com.javalab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

public class Journal {

    private Map<Integer, Task> tasks;

    public Journal(Map<Integer, Task> tasks) {
        this.tasks = tasks;
    }

    public Journal() {
        tasks = new HashMap<>();
    }

    public void setTasks(Map<Integer, Task> tasks) {
        this.tasks = tasks;
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

    @JsonIgnore
    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        for (Task task : tasks.values()) {
            taskList.add(task);
        }
        return Collections.unmodifiableList(taskList);
    }

    public Map<Integer, Task> getTasks() {
        return tasks;
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

