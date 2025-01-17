package org.example.data.dto;
import org.example.data.dto.features.Task;
import org.example.data.dto.flyweight.Status;
import org.example.data.dto.flyweight.StatusType;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class Project {
    private String name;
    private String description;
    private HashSet<Task> toDoTasks = new LinkedHashSet<>();
    private HashSet<Task> inProgressTasks = new LinkedHashSet<>();
    private HashSet<Task> completedTasks = new LinkedHashSet<>();

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashSet<Task> getToDoTasks() {
        return toDoTasks;
    }

    public HashSet<Task> getInProgressTasks() {
        return inProgressTasks;
    }

    public HashSet<Task> getCompletedTasks() {
        return completedTasks;
    }

    public void deleteTask(Task task ,HashSet<Task> source){
        if (source.contains(task)) {
            source.remove(task);
        }
        else{
            throw new IllegalArgumentException("Please entre a valid task");
        }
    }
    public HashSet<Task> getTasksByStatus(StatusType statusType) {
        if (statusType == StatusType.COMPLETED)
            return completedTasks;
        else if (statusType == StatusType.INPROGRESS)
            return inProgressTasks;
        else
            return toDoTasks;
    }
    public void addTask(Task task ,HashSet<Task> source){
        if (source == null)
            source = toDoTasks;
        if (task != null) {
            source.add(task);
            if (source.equals(toDoTasks)) {
                task.setStatus(new Status("ToDo"));
            } else if (source.equals(inProgressTasks)) {
                task.setStatus(new Status("InProgress"));
            } else if (source.equals(completedTasks)) {
                task.setStatus(new Status("Completed"));
            }
        }
        else{
            throw new IllegalArgumentException("Task can't be null");
        }
    }
    public void transferTask(Task task, HashSet<Task> source, HashSet<Task> destination) {
        if (source.contains(task)) {
            deleteTask(task, source);
            addTask(task, destination);
        }
        if (task == null) {
            throw new IllegalArgumentException("Task can't be null");
        }
    }
}
