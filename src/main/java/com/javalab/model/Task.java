package com.javalab.model;


import java.util.Date;
import java.util.Objects;

public class Task {
    private String taskName;
    private String description;
    private Date date;
    private int id;

    public Task() {
    }

    public Task(String taskName, String description, Date date, int id) {
        this.taskName = taskName;
        this.description = description;
        this.date = date;
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && taskName.equals(task.taskName) && description.equals(task.description) && date.equals(task.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, description, date, id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", id=" + id +
                '}';
    }
}
