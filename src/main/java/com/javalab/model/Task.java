package com.javalab.model;


import java.util.Date;
import java.util.Objects;

public class Task {
    private String taskName;
    private String description;
    private Date date;
    private int id;
    private Status taskStatus;

    public Task() {
    }

    public Task(String taskName, String description, Date date, int id, Status taskStatus) {
        this.taskName = taskName;
        this.description = description;
        this.date = date;
        this.id = id;
        this.taskStatus = taskStatus;
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

    public Status getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Status taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(taskName, task.taskName) && Objects.equals(description, task.description) && Objects.equals(date, task.date) && Objects.equals(taskStatus, task.taskStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, description, date, id, taskStatus);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", id=" + id +
                ", taskStatus='" + taskStatus + '\'' +
                '}';
    }
}
